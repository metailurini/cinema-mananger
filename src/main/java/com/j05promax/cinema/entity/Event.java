package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Event extends Entity {

    public String EventID = null;
    public Timestamp StartTime;
    public Timestamp EndTime;
    public String Title = null;
    public Timestamp CreatedAt;
    public Timestamp Updated;


    public static String TableName() {
 
        return "events";
    };

    public Event FromResultSet(ResultSet result) throws SQLException {
        EventID = result.getString("event_id");
        StartTime = result.getTimestamp("start_time");
        EndTime = result.getTimestamp("end_time");
        Title = result.getString("title");
        CreatedAt = result.getTimestamp("created_at");
        Updated = result.getTimestamp("updated_at");
        return this;
    }
}