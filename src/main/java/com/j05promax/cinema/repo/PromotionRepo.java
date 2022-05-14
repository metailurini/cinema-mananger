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
        ArrayList<Promotion> promotions = new ArrayList<Promotion>();

        String query = "select * from %s";
        ResultSet result = this.Query(String.format(query, Promotion.TableName()), (ParamSetter)(statement) -> {});

        while (result.next()) {
            promotions.add(new Promotion().FromResultSet(result));
        }

        this.Close();
        return promotions;
    }  

        public Promotion GetByID(String id) {
            return new Promotion();
        }
    
        public boolean Create(String id) {
            return true;
        }
    
        public boolean Update(String id, Promotion promotion) {
            return true;
        }
    
        public boolean Delete(String id) {
            return true;
        }
}
