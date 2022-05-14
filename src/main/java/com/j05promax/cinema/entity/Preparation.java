package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Preparation extends Entity {

    public String PreparationID = null;
    public String Status = null;
    public String Name = null;
    public int Duration = 0;
    public String Details = null;
    public Timestamp CreatedAt;
    public Timestamp Updated;


    public static String TableName() {
 
        return "preparations";
    };

    public Preparation FromResultSet(ResultSet result) throws SQLException {
        PreparationID = result.getString("preparation_id");
        Status = result.getString("status");
        Name = result.getString("name");
        Duration = result.getInt("duration");
        Details = result.getString("details");
        return this;
    }
}