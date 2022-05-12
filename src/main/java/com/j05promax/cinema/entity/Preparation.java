package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Preparation extends Entity {

    public String Preparation_id = null;
    public String Status = null;
    public String Name = null;
    public int Duration = 0;
    public String Details = null;


    public static String TableName() {
 
        return "preparations";
    };

    public Preparation FromResultSet(ResultSet result) throws SQLException {
        Preparation_id = result.getString("preparation_id");
        Status = result.getString("status");
        Name = result.getString("name");
        Duration = result.getInt("duration");
        Details = result.getString("details");
        return this;
    }
}