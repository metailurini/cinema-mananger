package com.j05promax.cinema.repo;


import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.j05promax.cinema.entity.Admin;
import com.j05promax.cinema.util.db.DBConnection;

public class AdminRepo extends Repository {

    public AdminRepo(DBConnection conn) {
        super(conn);
    }

    public ArrayList<Admin> GetAll() throws SQLException {
        ArrayList<Admin> Admins = new ArrayList<Admin>();
        return Admins;
    }
}
