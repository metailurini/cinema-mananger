package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Image extends Entity {

    public String ImageID = null;
    public String ImageType = null;
    public String Url = null;


    public static String TableName() {
 
        return "images";
    };

    public Image FromResultSet(ResultSet result) throws SQLException {
        ImageID = result.getString("image_id");
        ImageType = result.getString("image_type");
        Url = result.getString("url");
        return this;
    }
}