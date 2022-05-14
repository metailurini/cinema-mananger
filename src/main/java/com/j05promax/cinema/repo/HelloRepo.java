package com.j05promax.cinema.repo;


import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.j05promax.cinema.entity.Hello;
import com.j05promax.cinema.util.db.DBConnection;

public class HelloRepo extends Repository {

    public HelloRepo(DBConnection conn) {
        super(conn);
    }

    public ArrayList<Hello> GetAll() throws SQLException {
        ArrayList<Hello> hellos = new ArrayList<Hello>();

        String query = "select * from %s";
        ResultSet result = this.Query(String.format(query, Hello.TableName()), (ParamSetter)(statement) -> {});

        while (result.next()) {
            hellos.add(new Hello().FromResultSet(result));
        }

        this.Close();
        return hellos;
    }

    public Hello GetByID(String id) {
        return new Hello();
    }

    public boolean Create(String id) {
        return true;
    }

    public boolean Update(String id, Hello hello) {
        return true;
    }

    public boolean Delete(String id) {
        return true;
    }
}
