package com.j05promax.cinema.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import com.j05promax.cinema.util.db.DBConnection;

interface ParamSetter {
    void func(PreparedStatement statement) throws SQLException;
}

public class Repository {
    private DBConnection conn;
    private PreparedStatement statement;
    private ResultSet result;

    public Repository(DBConnection connection) {
        conn = connection;
    }

    public ResultSet Query(String queryStatement, ParamSetter paramSetter) throws SQLException {
        statement = conn.Connection().prepareStatement(queryStatement);
        paramSetter.func(statement);
        result = statement.executeQuery();

        return result;
    }

    public void Close() throws SQLException {
        statement.close();
        result.close();
    }

}
