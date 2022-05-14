package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Post extends Entity {

    public String PostID = null;
    public String Author = null;
    public String Title = null;
    public String Content = null;
    public Timestamp CreatedAt;
    public Timestamp Updated;


    public static String TableName() {
 
        return "posts";
    };

    public Post FromResultSet(ResultSet result) throws SQLException {
        PostID = result.getString("post_id");
        Author = result.getString("author");
        Title = result.getString("title");
        Content = result.getString("content");
        return this;
    }
}