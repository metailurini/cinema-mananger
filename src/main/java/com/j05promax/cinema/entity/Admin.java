package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin extends Entity {

    public String Admin_id = null;
    public String Password = null;
    public String Sec_code = null;
    public String Email = null;


    public static String TableName() {
 
        return "admins";
    };

    public Admin FromResultSet(ResultSet result) throws SQLException {
        Admin_id = result.getString("admin_id");
        Password = result.getString("password");
        Sec_code = result.getString("sec_code");
        Email = result.getString("email");
        return this;
    }
}