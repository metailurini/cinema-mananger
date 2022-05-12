package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Ticket extends Entity {

    public String Ticket_id = null;
    public String Status = null;
    public float Price = 0;
    public String Room_id = null;
    public String Seat_id = null;
    public String User_id = null;
    public String Admin_id = null;


    public static String TableName() {
 
        return "tickets";
    };

    public Ticket FromResultSet(ResultSet result) throws SQLException {
        Ticket_id = result.getString("ticket_id");
        Status = result.getString("status");
        Price = result.getFloat("price");
        Room_id = result.getString("room_id");
        Seat_id = result.getString("seat_id");
        User_id = result.getString("user_id");
        Admin_id = result.getString("admin_id");
        return this;
    }

    @Override
    public String toString() {
        return String.format("[ticket]: Ticket_id-> %s; Status-> %s; Price-> %s; Room_id-> %s; Seat_id-> %s; User_id-> %s; Admin_id-> %s", Ticket_id,Status,Price,Room_id,Seat_id,User_id,Admin_id);
    }
}
