package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Event extends Entity {

    public String EventID = null;
    public Timestamp StartTime;
    public Timestamp EndTime;
    public String Title = null;
    public String Content = null;
    public String Thumnail = null;
    public Timestamp CreatedAt;
    public Timestamp Updated;
    public String FormatCreatedAt;
    SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");


    public static String TableName() {
 
        return "events";
    };

    public Event FromResultSet(ResultSet result) throws SQLException {
        EventID = result.getString("event_id");
        StartTime = result.getTimestamp("start_time");
        EndTime = result.getTimestamp("end_time");
        Title = result.getString("title");
        Content = result.getString("content");
        Thumnail = result.getString("url");
        CreatedAt = result.getTimestamp("created_at");
        FormatCreatedAt = dateFormat.format(CreatedAt);
        Updated = result.getTimestamp("updated_at");
        return this;
    }
}