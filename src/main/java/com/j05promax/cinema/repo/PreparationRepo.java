package com.j05promax.cinema.repo;


import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.j05promax.cinema.entity.Preparation;
import com.j05promax.cinema.util.db.DBConnection;

public class PreparationRepo extends Repository {

    public PreparationRepo(DBConnection conn) {
        super(conn);
    }

    public ArrayList<Preparation> GetAll() throws SQLException {
        ArrayList<Preparation> Preparations = new ArrayList<Preparation>();
        return Preparations;
    }
}
