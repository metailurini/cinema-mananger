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
        ArrayList<Admin> admins = new ArrayList<Admin>();

        String query = "select * from %s";
        ResultSet result = this.Query(String.format(query, Admin.TableName()), (ParamSetter)(statement) -> {});

        while (result.next()) {
            admins.add(new Admin().FromResultSet(result));
        }

        this.Close();
        return admins;
    }

    public Admin GetByID(String id) {
        return new Admin();
    }

    public boolean Create(String id) {
        return true;
    }

    public boolean Update(String id, Admin admin) {
        return true;
    }

    public boolean Delete(String id) {
        return true;
    }
}