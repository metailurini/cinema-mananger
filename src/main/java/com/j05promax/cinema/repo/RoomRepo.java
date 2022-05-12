package com.j05promax.cinema.repo;


import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.j05promax.cinema.entity.Room;
import com.j05promax.cinema.util.db.DBConnection;

public class RoomRepo extends Repository {

    public RoomRepo(DBConnection conn) {
        super(conn);
    }

    public ArrayList<Room> GetAll() throws SQLException {
        ArrayList<Room> Rooms = new ArrayList<Room>();
        return Rooms;
    }
}
