package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Admin extends Entity {

    public String AdminID = "";
    public String Password = "";
    public String SecCode = "";
    public String Email = "";
    public String UserGroup = Admin.Staff;
    public Timestamp CreatedAt = new Timestamp(new Date().getTime());
    public Timestamp UpdatedAt = new Timestamp(new Date().getTime());
    public String FormatCreatedAt;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public String Status = "";

    public static String Staff = "Nhân Viên";
    public static String Manager = "Quản Trị Viên";


    public static String TableName() {
        return "admins";
    };

    public Admin FromResultSet(ResultSet result) throws SQLException {
        AdminID = result.getString("admin_id");
        Password = result.getString("password");
        SecCode = result.getString("sec_code");
        Email = result.getString("email");
        UserGroup = result.getString("user_group");
        CreatedAt = result.getTimestamp("created_at");
        FormatCreatedAt = dateFormat.format(CreatedAt);
        UpdatedAt = result.getTimestamp("updated_at");
        Status = result.getString("status");
        return this;
    }
}