package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Film extends Entity {

    public String FilmID = null;
    public String Name = null;
    public String Details = null;
    public float Price = 0;
    public int Duration = 0;
    public String Thumnail = null;
    public Timestamp CreatedAt;
    public Timestamp Updated;
    public String Category;
    public String FormatCreatedAt;
    SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");

    public static final String Active = "Đang chiếu";
    public static final String Archived = "Đã lưu trữ";

    public static String TableName() {
 
        return "films";
    };

    public Film FromResultSet(ResultSet result) throws SQLException {
        FilmID = result.getString("film_id");
        Name = result.getString("name");
        Details = result.getString("details");
        Price = result.getFloat("price");
        Duration = result.getInt("duration");
        Thumnail = result.getString("url");
        CreatedAt = result.getTimestamp("created_at");
        FormatCreatedAt = dateFormat.format(CreatedAt);
        Updated = result.getTimestamp("updated_at");
        Category = result.getString("category");
        return this;
    }

    @Override
    public String toString() {
        return String.format("[film]: id-> %s; name-> %s; details-> %s; price-> %s; duration-> %s; category-> %s;", FilmID, Name, Details, Price, Duration, Category);
    }
}