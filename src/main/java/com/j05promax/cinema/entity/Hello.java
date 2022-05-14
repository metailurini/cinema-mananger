package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Hello extends Entity {

    public String Message = null;


    public static String TableName() {
        return "hello";
    };

    public Hello FromResultSet(ResultSet result) throws SQLException {
        Message = result.getString("message");
        return this;
    }

    @Override
    public String toString() {
        // return "Hello {" + "Message='" + Message + "'" + "}";
        return String.format("[hello]: mess-> %s; ----%s", Message, Message);
    }
}

//Hello