package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Calendar extends Entity {

    public String CalendarID = null;
    public Timestamp StartTime;
    public Timestamp EndTime;
    public String RoomID = null;
    public String FilmID = null;
    public Timestamp CreatedAt;
    public Timestamp UpdatedAt;

    public static String TableName() {
        return "calendars";
    };

    public Calendar FromResultSet(ResultSet result) throws SQLException {
        CalendarID = result.getString("calendar_id");
        StartTime = result.getTimestamp("start_time");
        EndTime = result.getTimestamp("end_time");
        RoomID = result.getString("room_id");
        FilmID = result.getString("film_id");
        CreatedAt = result.getTimestamp("created_at");
        UpdatedAt = result.getTimestamp("updated_at");
        return this;
    }
}