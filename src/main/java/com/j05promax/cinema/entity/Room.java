package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Room extends Entity {

    public String Room_id = null;
    public String Name = null;
    public String Status = null;


    public static String TableName() {
 
        return "rooms";
    };

    public Room FromResultSet(ResultSet result) throws SQLException {
        Room_id = result.getString("room_id");
        Name = result.getString("name");
        Status = result.getString("status");
        return this;
    }
}