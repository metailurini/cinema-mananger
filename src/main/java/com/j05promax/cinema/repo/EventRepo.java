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

    public ArrayList<Event> GetAll() throws SQLException {
        ArrayList<Event> events = new ArrayList<Event>();

        String query = "select * from %s";
        ResultSet result = this.Query(String.format(query, Event.TableName()), (ParamSetter)(statement) -> {});

        while (result.next()) {
            events.add(new Event().FromResultSet(result));
        }

        this.Close();
        return events;
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
