package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Image extends Entity {

    public String Image_id = null;
    public String Image_type = null;
    public String Url = null;


    public static String TableName() {
 
        return "images";
    };

    public Image FromResultSet(ResultSet result) throws SQLException {
        Image_id = result.getString("image_id");
        Image_type = result.getString("image_type");
        Url = result.getString("url");
        return this;
    }
}