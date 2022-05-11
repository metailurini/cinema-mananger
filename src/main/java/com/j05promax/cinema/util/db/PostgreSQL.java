package com.j05promax.cinema.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQL implements DBConnection {
    private Connection conn;

    // jdbc:postgresql://localhost/test?user=fred&password=secret&ssl=true
    public void Open(String uri) throws SQLException {
        this.conn = DriverManager.getConnection(uri);
        System.out.println("PostgreSQL connected to " + uri);
    }

    public Connection Connection() {
        return this.conn;
    }

    public void Close() throws SQLException {
        this.conn.close();
    }
}