package com.j05promax.cinema.repo;


import java.sql.SQLException;
import java.util.ArrayList;

import com.j05promax.cinema.entity.Location;
import com.j05promax.cinema.util.db.DBConnection;

public class LocationRepo extends Repository {

    public LocationRepo(DBConnection conn) {
        super(conn);
    }

    public ArrayList<Location> GetAll() throws SQLException {
        ArrayList<Location> locations = new ArrayList<Location>();

        String query = "select * from locations where location_id = ?";
        WrapStatementParams func = (statement) -> { statement.setString(1, "01FR4M51XJY9E77GSN4QZ1Q9N3"); };

        this.Query(query, func);
        while (this.result.next()) {
            locations.add(new Location(this.result));
        }

        this.Close();
        return locations;
    }
}
