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
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();

        String query = "SELECT * FROM %s";
        ResultSet result = this.Query(String.format(query,Ticket.TableName()),(ParamSetter)(statement) -> {});
        
        while (result.next()){
            tickets.add(new Ticket().FromResultSet(result));
        }

        this.Close();
        return tickets;
    }

    public Ticket GetByID(String id){
        return new Ticket();
    }

    public boolean Create(String id){
        return true;
    }

    public boolean Update(String id, Ticket ticket){
        return true;
    }

    public boolean Delete(String id){
        return true;
    }
}