package com.j05promax.cinema.repo;


import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.j05promax.cinema.entity.Seat;
import com.j05promax.cinema.util.db.DBConnection;

public class SeatRepo extends Repository {

    public SeatRepo(DBConnection conn) {
        super(conn);
    }

    public ArrayList<Seat> GetAll() throws SQLException {
        ArrayList<Seat> Seats = new ArrayList<Seat>();
        return Seats;
    }
}
