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
        ArrayList<Room> rooms = new ArrayList<Room>();

        String query = "select * from %s";
        ResultSet result = this.Query(String.format(query, Room.TableName()), (ParamSetter)(statement) -> {});

        while (result.next()) {
            rooms.add(new Room().FromResultSet(result));
        }

        this.Close();
        return rooms;
    }
    
    public Room GetByID(String id) {
        return new Room();
    }

    public boolean Create(String id) {
        return true;
    }

    public boolean Update(String id, Room room) {
        return true;
    }

    public boolean Delete(String id) {
        return true;
    }
}
