package com.j05promax.cinema.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Promotion extends Entity {

    public String PromotionID = null;
    public String Name = null;
    public String TicketID = null;
    public Boolean IsStudent = null;
    public float NumberValue = 0;
    public String StringValue = null;


    public static String TableName() {
 
        return "promotions";
    };

    public Promotion FromResultSet(ResultSet result) throws SQLException {
        PromotionID = result.getString("promotion_id");
        Name = result.getString("name");
        TicketID = result.getString("ticket_id");
        IsStudent = result.getBoolean("is_student");
        NumberValue = result.getFloat("number_value");
        StringValue = result.getString("string_value");
        return this;
    }
}