package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Promotion extends Entity {

    public String Promotion_id = null;
    public String Name = null;
    public String Ticket_id = null;
    public Boolean Is_student = null;
    public float Number_value = 0;
    public String String_value = null;


    public static String TableName() {
 
        return "promotions";
    };

    public Promotion FromResultSet(ResultSet result) throws SQLException {
        Promotion_id = result.getString("promotion_id");
        Name = result.getString("name");
        Ticket_id = result.getString("ticket_id");
        Is_student = result.getBoolean("is_student");
        Number_value = result.getFloat("number_value");
        String_value = result.getString("string_value");
        return this;
    }
}