package com.j05promax.cinema.util.log;

import java.io.StringWriter;
import java.io.PrintWriter;
import java.util.Date;

public class Log {
    private StringWriter sw;
    private PrintWriter pw;
    
    public Log(Exception err) {
        this.sw = new StringWriter();
        this.pw = new PrintWriter(sw);
        err.printStackTrace(pw);
    }

    // to string
    public void Show() {
        System.out.println(String.format("[%s]::%s",new Date().toString(), this.sw.toString()));;
    }
}
