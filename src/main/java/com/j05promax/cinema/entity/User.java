package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends Entity {

    public String User_id = null;
    public String Full_name = null;
    public String Admin_id = null;
    public String Phone_number = null;
    public String Email = null;


    public static String TableName() {
 
        return "users";
    };

    public User FromResultSet(ResultSet result) throws SQLException {
        User_id = result.getString("user_id");
        Full_name = result.getString("full_name");
        Admin_id = result.getString("admin_id");
        Phone_number = result.getString("phone_number");
        Email = result.getString("email");
        return this;
    }
}