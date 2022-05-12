package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Post extends Entity {

    public String Post_id = null;
    public String Author = null;
    public String Title = null;
    public String Content = null;


    public static String TableName() {
 
        return "posts";
    };

    public Post FromResultSet(ResultSet result) throws SQLException {
        Post_id = result.getString("post_id");
        Author = result.getString("author");
        Title = result.getString("title");
        Content = result.getString("content");
        return this;
    }
}