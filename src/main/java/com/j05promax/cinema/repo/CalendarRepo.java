package com.j05promax.cinema.repo;


import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.j05promax.cinema.entity.Calendar;
import com.j05promax.cinema.util.db.DBConnection;

public class CalendarRepo extends Repository {

    public CalendarRepo(DBConnection conn) {
        super(conn);
    }

    public ArrayList<Calendar> GetAll() throws SQLException {
        ArrayList<Calendar> Calendars = new ArrayList<Calendar>();
        return Calendars;
    }
}
