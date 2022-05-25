package com.j05promax.cinema.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.j05promax.cinema.entity.Calendar;
import com.j05promax.cinema.util.common.Common;
import com.j05promax.cinema.util.db.DBConnection;

public class CalendarRepo extends Repository {

    public CalendarRepo(DBConnection conn) {
        super(conn);
    }

    public ArrayList<Calendar> GetAll(String filmID) throws SQLException {
        ArrayList<Calendar> calendars = new ArrayList<Calendar>();
        String query = "SELECT * FROM %s";

        if (filmID != null && filmID != "") {
            query += " where film_id = ?";
        }

        ResultSet result = this.Query(
                String.format(query, Calendar.TableName()),
                (ParamSetter) (statement) -> {
                    if (filmID != null && filmID != "") {
                        statement.setString(1, filmID);
                    }
                });

        while (result.next()) {
            calendars.add(new Calendar().FromResultSet(result));
        }

        this.Close();
        return calendars;
    }

    public Calendar GetByID(String id) {
        return new Calendar();
    }

    public Calendar Create(Calendar calendars) throws SQLException {
        Common cm = Common.getInstance();
        String query = "INSERT INTO %s (calendar_id, start_time, end_time, room_id, film_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING calendar_id";

        calendars.CalendarID = cm.GetUID();
        calendars.CreatedAt = new Timestamp(new Date().getTime());
        calendars.UpdatedAt = new Timestamp(new Date().getTime());

        ResultSet result = this.Query(
                String.format(query, Calendar.TableName()),
                (ParamSetter) (statement) -> {
                    statement.setString(1, calendars.CalendarID);
                    statement.setTimestamp(2, calendars.StartTime);
                    statement.setTimestamp(3, calendars.EndTime);
                    statement.setString(4, calendars.RoomID);
                    statement.setString(5, calendars.FilmID);
                    statement.setTimestamp(6, calendars.CreatedAt);
                    statement.setTimestamp(7, calendars.UpdatedAt);
                });

        while (result.next()) {
        }

        return calendars;
    }

    // public ArrayList<Calendar> InsertBulk(ArrayList<Calendar> calendars) throws
    // SQLException {
    // Common cm = Common.getInstance();
    // String query = "INSERT INTO %s (calendar_id, start_time, end_time, room_id,
    // film_id, created_at, updated_at) VALUES ";
    // ArrayList<String> values = new ArrayList<>();

    // for(int index = 0; index < calendars.size(); index++) {
    // calendars.get(index).CalendarID = cm.GetUID();
    // System.out.println(calendars.get(index).CalendarID);
    // calendars.get(index).CreatedAt = new Timestamp(new Date().getTime());
    // calendars.get(index).UpdatedAt = new Timestamp(new Date().getTime());
    // values.add("(?, ?, ?, ?, ?, ?, ?)");
    // }
    // query += String.join(",", values) + " RETURNING calendar_id";
    // System.out.println(query);

    // ResultSet result = this.Query(
    // String.format(query,Calendar.TableName()),
    // (ParamSetter)(statement) -> {
    // for(int index = 0; index < calendars.size(); index++) {;
    // Calendar cal = calendars.get(index);
    // statement.setString(index*7+1, cal.CalendarID);
    // statement.setTimestamp(index*7+2, cal.StartTime);
    // statement.setTimestamp(index*7+3, cal.EndTime);
    // statement.setString(index*7+4, cal.RoomID);
    // statement.setString(index*7+5, cal.FilmID);
    // statement.setTimestamp(index*7+6, cal.CreatedAt);
    // statement.setTimestamp(index*7+7, cal.UpdatedAt);
    // }
    // }
    // );

    // while (result.next()){ }

    // return calendars;
    // }

    public boolean Update(String id, Calendar calendar) {
        return true;
    }

    public boolean Delete(String id) {
        return true;
    }
}
