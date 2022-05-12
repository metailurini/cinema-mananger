package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Film extends Entity {

    public String FilmID = null;
    public String Name = null;
    public String Details = null;
    public float Price = 0;
    public int Duration = 0;


    public static String TableName() {
 
        return "films";
    };

    public Film FromResultSet(ResultSet result) throws SQLException {
        FilmID = result.getString("film_id");
        Name = result.getString("name");
        Details = result.getString("details");
        Price = result.getFloat("price");
        Duration = result.getInt("duration");
        return this;
    }
}