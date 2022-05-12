package com.j05promax.cinema.repo;


import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.j05promax.cinema.entity.Image;
import com.j05promax.cinema.util.db.DBConnection;

public class ImageRepo extends Repository {

    public ImageRepo(DBConnection conn) {
        super(conn);
    }

    public ArrayList<Image> GetAll() throws SQLException {
        ArrayList<Image> Images = new ArrayList<Image>();
        return Images;
    }
}
