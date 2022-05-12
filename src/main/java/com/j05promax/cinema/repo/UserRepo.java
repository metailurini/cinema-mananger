package com.j05promax.cinema.repo;


import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.j05promax.cinema.entity.User;
import com.j05promax.cinema.util.db.DBConnection;

public class UserRepo extends Repository {

    public UserRepo(DBConnection conn) {
        super(conn);
    }

    public ArrayList<User> GetAll() throws SQLException {
        ArrayList<User> Users = new ArrayList<User>();
        return Users;
    }
}
