package com.j05promax.cinema.repo;


import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.j05promax.cinema.entity.Event;
import com.j05promax.cinema.util.db.DBConnection;

public class EventRepo extends Repository {

    public EventRepo(DBConnection conn) {
        super(conn);
    }

    public ArrayList<Event> GetAll(String search) throws SQLException {
        ArrayList<Event> events = new ArrayList<Event>();

        String query = (
            "SELECT " +
            "events.event_id, " +
            "events.start_time, " +
            "events.end_time, " +
            "events.title, " +
            "events.content, " +
            "events.created_at, " +
            "events.updated_at, " +
            "images.url " +
            "FROM " +
            "events " +
            "LEFT JOIN images ON " +
            "images.image_id = events.event_id AND " +
            "images.image_type = 'events' " +
            "WHERE LOWER(title) LIKE ?"
        );
        ResultSet result = this.Query(String.format(query, Event.TableName()), 
        (ParamSetter)(statement) -> {
            statement.setString(1,("%" + search + "%").toLowerCase());
        });

        while (result.next()) {
            events.add(new Event().FromResultSet(result));
        }

        this.Close();
        return events;
    }

    public int CountEvent() throws SQLException{
        int count_event = 0;
        String query = "SELECT COUNT(*) AS counted FROM events";
        ResultSet result = this.Query(query, (ParamSetter)(statement) -> {});
        if(result.next()){
            count_event = result.getInt("counted");
        }
        this.Close();
        return count_event;
    }

    public Event GetByID(String id) {
        return new Event();
    }

    public boolean Create(String id) {
        return true;
    }

    public boolean Update(String id, Event event) {
        return true;
    }

    public boolean Delete(String id) {
        return true;
    }
}
