package com.j05promax.cinema.repo;


import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.j05promax.cinema.entity.User;
import com.j05promax.cinema.util.db.DBConnection;

public class UserRepo extends Repository {

    public UserRepo(DBConnection conn) {
        super(conn);
    }

    public ArrayList<User> GetAll(String search) throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
        
        String query = "select * from %s where LOWER(full_name) like ? OR phone_number like ?";
        ResultSet result = this.Query(
            String.format(query, User.TableName()),
            (ParamSetter)(statement) -> {
                statement.setString(1, ("%" + search + "%").toLowerCase());
                statement.setString(2, ("%" + search + "%").toLowerCase());
            }
        );

        while (result.next()) {
            users.add(new User().FromResultSet(result));
        }

        this.Close();
        return users;
    }

    public int CountCustomer(String search) throws SQLException {
        int count_customer = 0;
        String query = "select count(*) as counted from %s where LOWER(full_name) like ? OR phone_number like ?";
        ResultSet result = this.Query(
            String.format(query, User.TableName()),
            (ParamSetter)(statement) -> {
                statement.setString(1, ("%" + search + "%").toLowerCase());
                statement.setString(2, ("%" + search + "%").toLowerCase());
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

    public boolean Create(String id) {
        return true;
    }

    public boolean Update(String id, User user) {
        return true;
    }

    public boolean Delete(String id) {
        return true;
    }
}
