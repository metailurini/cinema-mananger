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
        ArrayList<Image> images = new ArrayList<Image>();

        String query = "select * from %s";
        ResultSet result = this.Query(String.format(query, Image.TableName()), (ParamSetter)(statement) -> {});

        while (result.next()) {
            images.add(new Image().FromResultSet(result));
        }

        this.Close();
        return images;
    }

    public Image GetByID(String id) {
        return new Image();
    }

    public boolean Create(String id) {
        return true;
    }

    public boolean Update(String id, Image image) {
        return true;
    }

    public boolean Delete(String id) {
        return true;
    }
}
