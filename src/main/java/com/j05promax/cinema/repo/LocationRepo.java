package com.j05promax.cinema.repo;


import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.j05promax.cinema.entity.Location;
import com.j05promax.cinema.util.db.DBConnection;

public class LocationRepo extends Repository {

    public LocationRepo(DBConnection conn) {
        super(conn);
    }

    public ArrayList<Location> GetAll() throws SQLException {
        ArrayList<Location> locations = new ArrayList<Location>();

        String query = "select * from %s where location_id = ?";
        ParamSetter paramSetter = (statement) -> { statement.setString(1, "01FR4M51XJY9E77GSN4QZ1Q9N3"); };
        ResultSet result = this.Query(String.format(query, Location.TableName()), paramSetter);

        while (result.next()) {
            locations.add(new Location().FromResultSet(result));
        }

        this.Close();
        return locations;
    }
}
