package com.j05promax.cinema.repo;


import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.j05promax.cinema.entity.Film;
import com.j05promax.cinema.util.db.DBConnection;

public class FilmRepo extends Repository {

    public FilmRepo(DBConnection conn) {
        super(conn);
    }

    public ArrayList<Film> GetAll() throws SQLException {
        ArrayList<Film> Films = new ArrayList<Film>();
        return Films;
    }
}
