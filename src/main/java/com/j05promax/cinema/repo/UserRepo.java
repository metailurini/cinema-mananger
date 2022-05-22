package com.j05promax.cinema.repo;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.j05promax.cinema.entity.User;
import com.j05promax.cinema.util.common.Common;
import com.j05promax.cinema.util.db.DBConnection;
import com.j05promax.cinema.util.log.Log;

public class UserRepo extends Repository {

    public UserRepo(DBConnection conn) {
        super(conn);
    }

    public ArrayList<User> GetAll(String search, String status, Repository.Perpage perpage) throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
        String perpageQuery = String.format("offset %d limit %d", perpage.maxInPage * perpage.page, perpage.maxInPage);
        
        String query = "SELECT * FROM %s WHERE (LOWER(full_name) LIKE ? OR phone_number LIKE ?) " + (status.equals("") ? "" : " AND status LIKE ? " + perpageQuery + " ORDER BY created_at DESC ");
        ResultSet result = this.Query(
            String.format(query, User.TableName()),
            (ParamSetter)(statement) -> {
                statement.setString(1, ("%" + search + "%").toLowerCase());
                statement.setString(2, ("%" + search + "%").toLowerCase());
                if (!status.equals("")) {
                    statement.setString(3, status);
                }
            }
        );

        while (result.next()) {
            users.add(new User().FromResultSet(result));
        }

        this.Close();
        return users;
    }

    public int CountCustomer(String search, String status) throws SQLException {
        int count_customer = 0;
        String query = "SELECT COUNT(*) as counted FROM %s WHERE (LOWER(full_name) LIKE ? OR phone_number LIKE ?) " + (status.equals("") ? "" : " AND status LIKE ?");
        ResultSet result = this.Query(
            String.format(query, User.TableName()),
            (ParamSetter)(statement) -> {
                statement.setString(1, ("%" + search + "%").toLowerCase());
                statement.setString(2, ("%" + search + "%").toLowerCase());
                if (!status.equals("")) {
                    statement.setString(3, status);
                }
            }
        );
        if (result.next()) {
            count_customer = result.getInt("counted");
        }
        this.Close();
        return count_customer;
    }

    public User GetByID(String id) throws SQLException{
        String query = "SELECT * FROM %s WHERE user_id = ?";
        User user = null;
        ResultSet result = this.Query(String.format(query,User.TableName()),
        (ParamSetter)(statement) -> {
            statement.setString(1, id);
        });
        if(result.next()){
            user = new User().FromResultSet(result);
        }

        this.Close();
        return user;
    }

    public boolean Create(User user) {
        Common cm = Common.getInstance();
        user.UserID = cm.GetUID();
        String query = "INSERT INTO %s (user_id,full_name,admin_id,phone_number, status) VALUES(?,?,?,?,?) RETURNING user_id;" ;
        try {
            ResultSet result = this.Query(
                String.format(query,User.TableName()),(ParamSetter)(statement) -> {
                    statement.setString(1, user.UserID);
                    statement.setString(2, user.FullName);
                    statement.setString(3, user.AdminID);
                    statement.setString(4, user.PhoneNumber);
                    statement.setString(5, user.Status);
                });

            if (result.next()) {
                if (result.getString("user_id").contentEquals("")) {
                    return false;
                }
            }
        
        } catch (SQLException e) {
            new Log(e).Show();
            return false;
        }

        return true;
    }

    public Repository.Error Update(User user) {
        user.UpdatedAt = new Timestamp(new Date().getTime());
        try {
            String query = "UPDATE %s SET user_id = ?, full_name = ?, admin_id = ?, phone_number = ?, email = ?, created_at = ?, updated_at = ?, status = ? WHERE user_id = ? RETURNING user_id;";
            ResultSet resultSet = this.Query(String.format(query, User.TableName()),
                    (ParamSetter) (statement) -> {
                        statement.setString(1, user.UserID);
                        statement.setString(2, user.FullName);
                        statement.setString(3, user.AdminID);
                        statement.setString(4, user.PhoneNumber);
                        statement.setString(5, user.Email);
                        statement.setTimestamp(6, user.CreatedAt);
                        statement.setTimestamp(7, user.UpdatedAt);
                        statement.setString(8, user.Status);
                        statement.setString(9, user.UserID);
                    });
            if (resultSet.next()) {
                if (!resultSet.getString("user_id").contentEquals(user.UserID)) {
                    return Repository.Error.CanNotUpdate();
                }
            }
        } catch (SQLException e) {
            new Log(e).Show();
            if (e.getSQLState().contentEquals(Repository.Error.Unique)) {
                return new Repository.Error("số điện thoại bị trùng", Repository.Error.Unique);
            }
            return new Repository.Error(e.getMessage(), e.getSQLState());
        }


        return null;
    }

    public boolean Delete(String id) {
        return true;
    }
}
