package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Seat extends Entity {

    public String Seat_id = null;
    public String Room_id = null;
    public String Name = null;
    public float Bonus_price = 0;
    public String Status = null;


    public static String TableName() {
 
        return "seats";
    };

    public Seat FromResultSet(ResultSet result) throws SQLException {
        Seat_id = result.getString("seat_id");
        Room_id = result.getString("room_id");
        Name = result.getString("name");
        Bonus_price = result.getFloat("bonus_price");
        Status = result.getString("status");
        return this;
    }
}