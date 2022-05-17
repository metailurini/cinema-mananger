package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Admin extends Entity {

    public String AdminID = "";
    public String Password = "";
    public String SecCode = "";
    public String Email = "";
    public Timestamp CreatedAt = null;
    public Timestamp UpdatedAt = null;


    public static String TableName() {
        return "admins";
    };

    public Admin FromResultSet(ResultSet result) throws SQLException {
        AdminID = result.getString("admin_id");
        Password = result.getString("password");
        SecCode = result.getString("sec_code");
        Email = result.getString("email");
        CreatedAt = result.getTimestamp("created_at");
        UpdatedAt = result.getTimestamp("updated_at");

        return this;
    }
}