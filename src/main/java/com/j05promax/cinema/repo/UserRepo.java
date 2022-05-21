package com.j05promax.cinema.repo;


import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

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
        
        String query = "SELECT * FROM %s WHERE (LOWER(full_name) LIKE ? OR phone_number LIKE ?) " + (status.equals("") ? "" : " AND status LIKE ?");
        ResultSet result = this.Query(
            String.format(query, User.TableName()) + perpageQuery,
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
        String query = "SELECT count(*) as counted FROM %s WHERE (LOWER(full_name) LIKE ? OR phone_number LIKE ?) " + (status.equals("") ? "" : " AND status LIKE ?");
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

    public User GetByID(String id) {
        return new User();
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

    public boolean Update(User user) {
        return true;
    }

    public boolean Delete(String id) {
        return true;
    }
}
