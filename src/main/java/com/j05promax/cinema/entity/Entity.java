package com.j05promax.cinema.entity;

import java.util.Calendar;
import java.util.TimeZone;

public class Entity {
    public static final Calendar tzUTC = Calendar.getInstance(TimeZone.getTimeZone("UTC"));  
    public static String TableName() {
        return "";
    };
}
