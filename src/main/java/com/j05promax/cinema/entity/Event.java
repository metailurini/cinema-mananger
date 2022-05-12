package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Event extends Entity {

    public String Event_id = null;
    public Timestamp Start_time;
    public Timestamp End_time;
    public String Title = null;


    public static String TableName() {
 
        return "events";
    };

    public Event FromResultSet(ResultSet result) throws SQLException {
        Event_id = result.getString("event_id");
        Start_time = result.getTimestamp("start_time");
        End_time = result.getTimestamp("end_time");
        Title = result.getString("title");
        return this;
    }
}