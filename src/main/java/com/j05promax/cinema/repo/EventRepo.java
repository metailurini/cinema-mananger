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
        ArrayList<Event> Events = new ArrayList<Event>();
        return Events;
    }
}
