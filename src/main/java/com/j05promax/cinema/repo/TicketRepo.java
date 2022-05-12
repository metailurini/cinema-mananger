package com.j05promax.cinema.repo;


import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.j05promax.cinema.entity.Ticket;
import com.j05promax.cinema.util.db.DBConnection;

public class TicketRepo extends Repository {

    public TicketRepo(DBConnection conn) {
        super(conn);
    }

    public ArrayList<Ticket> GetAll() throws SQLException {
        ArrayList<Ticket> Tickets = new ArrayList<Ticket>();
        return Tickets;
    }
}
