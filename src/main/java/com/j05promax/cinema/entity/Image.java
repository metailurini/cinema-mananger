package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class Image extends Entity {

    public String ImageID = null;
    public String ImageType = null;
    public String Url = null;
    public Timestamp CreatedAt = new Timestamp(new Date().getTime());
    public Timestamp Updated = new Timestamp(new Date().getTime());


    public static String TableName() {
 
        return "images";
    };

    public Image FromResultSet(ResultSet result) throws SQLException {
        ImageID = result.getString("image_id");
        ImageType = result.getString("image_type");
        Url = result.getString("url");
        CreatedAt = result.getTimestamp("created_at");
        Updated = result.getTimestamp("updated_at");
        return this;
    }
}