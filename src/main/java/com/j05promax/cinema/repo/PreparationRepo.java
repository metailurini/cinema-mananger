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
        ArrayList<Preparation> preparations = new ArrayList<Preparation>();

        String query = "select * from %s";
        ResultSet result = this.Query(String.format(query, Preparation.TableName()), (ParamSetter)(statement) -> {});

        while (result.next()) {
            preparations.add(new Preparation().FromResultSet(result));
        }

        this.Close();
        return preparations;
    }

    public Preparation GetByID(String id) {
        return new Preparation();
    }

    public boolean Create(String id) {
        return true;
    }

    public boolean Update(String id, Preparation preparation) {
        return true;
    }

    public boolean Delete(String id) {
        return true;
    }
}
