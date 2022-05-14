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
        ArrayList<Post> posts = new ArrayList<Post>();

        String query = "select * from %s";
        ResultSet result = this.Query(String.format(query, Post.TableName()), (ParamSetter)(statement) -> {});

        while (result.next()) {
            posts.add(new Post().FromResultSet(result));
        }

        this.Close();
        return posts;
    }

    public Post GetByID(String id) {
        return new Post();
    }

    public boolean Create(String id) {
        return true;
    }

    public boolean Update(String id, Post post) {
        return true;
    }

    public boolean Delete(String id) {
        return true;
    }
}
