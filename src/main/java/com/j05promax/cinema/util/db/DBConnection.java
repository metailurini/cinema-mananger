package com.j05promax.cinema.util.db;


import java.sql.Connection;
import java.sql.SQLException;


public interface DBConnection {
    public void Open(String uri) throws SQLException;
    public Connection Connection();
    public void Close() throws SQLException;
}