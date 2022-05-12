package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Calendar extends Entity {

    public String Calendar_id = null;
    public Timestamp Start_time;
    public Timestamp End_time;
    public String Room_id = null;
    public String Film_id = null;


    public static String TableName() {
 
        return "calendars";
    };

    public Calendar FromResultSet(ResultSet result) throws SQLException {
        Calendar_id = result.getString("calendar_id");
        Start_time = result.getTimestamp("start_time");
        End_time = result.getTimestamp("end_time");
        Room_id = result.getString("room_id");
        Film_id = result.getString("film_id");
        return this;
    }
}