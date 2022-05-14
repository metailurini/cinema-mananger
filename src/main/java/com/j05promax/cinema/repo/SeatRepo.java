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
        ArrayList<Seat> seats = new ArrayList<Seat>();

        String query = "select * from %s";
        ResultSet result = this.Query(String.format(query, Seat.TableName()), (ParamSetter)(statement) -> {});

        while (result.next()) {
            seats.add(new Seat().FromResultSet(result));
        }

        this.Close();
        return seats;
    }

    public Seat GetByID(String id) {
        return new Seat();
    }

    public boolean Create(String id) {
        return true;
    }

    public boolean Update(String id, Seat seat) {
        return true;
    }

    public boolean Delete(String id) {
        return true;
    }
}
