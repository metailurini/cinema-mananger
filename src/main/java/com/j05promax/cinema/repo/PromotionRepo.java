package com.j05promax.cinema.repo;


import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.j05promax.cinema.entity.Promotion;
import com.j05promax.cinema.util.db.DBConnection;

public class PromotionRepo extends Repository {

    public PromotionRepo(DBConnection conn) {
        super(conn);
    }

    public ArrayList<Promotion> GetAll() throws SQLException {
        ArrayList<Promotion> Promotions = new ArrayList<Promotion>();
        return Promotions;
    }
}
