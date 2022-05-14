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
        ArrayList<Calendar> calendars = new ArrayList<Calendar>();
        
        String query = "SELECT * FROM %s";
        ResultSet result = this.Query(String.format(query,Calendar.TableName()),(ParamSetter)(statement) -> {});
        
        while (result.next()){
            calendars.add(new Calendar().FromResultSet(result));
        }

        this.Close();
        return calendars;
    }

    public Calendar GetByID(String id) {
        return new Calendar();
    }

    public boolean Create(String id) {
        return true;
    }

    public boolean Update(String id, Calendar calendar) {
        return true;
    }

    public boolean Delete(String id) {
        return true;
    }
}
