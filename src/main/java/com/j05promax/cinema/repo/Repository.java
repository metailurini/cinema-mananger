package com.j05promax.cinema.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import com.j05promax.cinema.util.db.DBConnection;

public class Repository {
    protected DBConnection conn;
    protected PreparedStatement statement;
    protected ResultSet result;

    public Repository(DBConnection conn) {
        this.conn = conn;
    }

    public void Query(String queryStatement, WrapStatementParams wFunc) throws SQLException {
        this.statement = this.conn.Connection().prepareStatement(queryStatement);
        wFunc.func(statement);
        this.result = statement.executeQuery();
    }

    public void Close() throws SQLException {
        this.statement.close();
        this.result.close();
    }

}


interface WrapStatementParams {
    void func(PreparedStatement statement) throws SQLException;
}