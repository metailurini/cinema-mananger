package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;;

public class User extends Entity {

    public String UserID = null;
    public String FullName = null;
    public String AdminID = null;
    public String PhoneNumber = null;
    public String Email = null;
    public Timestamp CreatedAt = new Timestamp(new Date().getTime());
    public Timestamp UpdatedAt = new Timestamp(new Date().getTime());
    public String Status = "Hoạt động";

    public static String TableName() {
 
        return "users";
    };

    public User FromResultSet(ResultSet result) throws SQLException {
        UserID = result.getString("user_id");
        FullName = result.getString("full_name");
        AdminID = result.getString("admin_id");
        PhoneNumber = result.getString("phone_number");
        Email = result.getString("email");
        CreatedAt = result.getTimestamp("created_at");
        UpdatedAt = result.getTimestamp("updated_at");
        Status = result.getString("status");
        return this;
    }
}