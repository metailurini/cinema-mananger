package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Ticket extends Entity {

    public String TicketID = null;
    public String Status = null;
    public float Price = 0;
    public String RoomID = null;
    public String SeatID = null;
    public String UserID = null;
    public String AdminID = null;


    public static String TableName() {
 
        return "tickets";
    };

    public Ticket FromResultSet(ResultSet result) throws SQLException {
        TicketID = result.getString("ticket_id");
        Status = result.getString("status");
        Price = result.getFloat("price");
        RoomID = result.getString("room_id");
        SeatID = result.getString("seat_id");
        UserID = result.getString("user_id");
        AdminID = result.getString("admin_id");
        return this;
    }

    @Override
    public String toString() {
        return String.format("[ticket]: Ticket_id-> %s; Status-> %s; Price-> %s; Room_id-> %s; Seat_id-> %s; User_id-> %s; Admin_id-> %s", TicketID,Status,Price,RoomID,SeatID,UserID,AdminID);
    }
}
