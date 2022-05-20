package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Seat extends Entity {

    public String SeatID = null;
    public String RoomID = null;
    public String Name = null;
    public float BonusPrice = 0;
    public String Status = null;
    public Timestamp CreatedAt;
    public Timestamp Updated;


    public static String TableName() {
 
        return "seats";
    };

    public Seat FromResultSet(ResultSet result) throws SQLException {
        SeatID = result.getString("seat_id");
        RoomID = result.getString("room_id");
        Name = result.getString("name");
        BonusPrice = result.getFloat("bonus_price");
        Status = result.getString("status");
        CreatedAt = result.getTimestamp("created_at");
        Updated = result.getTimestamp("updated_at");
        return this;
    }
}