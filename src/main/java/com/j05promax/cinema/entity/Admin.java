package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin extends Entity {

    public String AdminID = null;
    public String Password = null;
    public String SecCode = null;
    public String Email = null;


    public static String TableName() {
 
        return "admins";
    };

    public Admin FromResultSet(ResultSet result) throws SQLException {
        AdminID = result.getString("admin_id");
        Password = result.getString("password");
        SecCode = result.getString("sec_code");
        Email = result.getString("email");
        return this;
    }
}