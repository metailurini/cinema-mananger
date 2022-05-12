package com.j05promax.cinema.repo;


import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.j05promax.cinema.entity.Post;
import com.j05promax.cinema.util.db.DBConnection;

public class PostRepo extends Repository {

    public PostRepo(DBConnection conn) {
        super(conn);
    }

    public ArrayList<Post> GetAll() throws SQLException {
        ArrayList<Post> Posts = new ArrayList<Post>();
        return Posts;
    }
}
