package com.j05promax.cinema.repo;


import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.j05promax.cinema.entity.Event;
import com.j05promax.cinema.entity.Image;
import com.j05promax.cinema.util.common.Common;
import com.j05promax.cinema.util.db.DBConnection;
import com.j05promax.cinema.util.log.Log;

public class EventRepo extends Repository {

    public EventRepo(DBConnection conn) {
        super(conn);
    }

    public ArrayList<Event> GetAll(String search) throws SQLException {
        ArrayList<Event> events = new ArrayList<Event>();

        String query = (
            "SELECT " +
            "events.event_id, " +
            "events.start_time, " +
            "events.end_time, " +
            "events.title, " +
            "events.content, " +
            "events.created_at, " +
            "events.updated_at, " +
            "images.url " +
            "FROM " +
            "events " +
            "LEFT JOIN images ON " +
            "images.image_id = events.event_id AND " +
            "images.image_type = 'events' " +
            "WHERE LOWER(title) LIKE ?"
        );
        ResultSet result = this.Query(String.format(query, Event.TableName()), 
        (ParamSetter)(statement) -> {
            statement.setString(1,("%" + search + "%").toLowerCase());
        });

        while (result.next()) {
            events.add(new Event().FromResultSet(result));
        }

        this.Close();
        return events;
    }

    public int CountEvent() throws SQLException{
        int count_event = 0;
        String query = "SELECT COUNT(*) AS counted FROM events";
        ResultSet result = this.Query(query, (ParamSetter)(statement) -> {});
        if(result.next()){
            count_event = result.getInt("counted");
        }
        this.Close();
        return count_event;
    }

    public Event GetByID(String id) {
        return new Event();
    }

    public boolean Create(Event event, Image image) {
        Common cm = Common.getInstance();
        
        event.EventID = cm.GetUID();
        String query = "INSERT INTO %s (event_id,title,content,created_at) VALUES(?,?,?,?) RETURNING event_id;";

        try{
            ResultSet result = this.Query(
                String.format(query,Event.TableName()),(ParamSetter)(statement) -> {
                    statement.setString(1, event.EventID);
                    statement.setString(2, event.Title);
                    statement.setString(3, event.Content);
                    statement.setTimestamp(4, event.CreatedAt);
                });
                if (result.next()) {
                    if (result.getString("event_id").contentEquals("")) {
                        return false;
                    }
                }
        }
        catch (SQLException e) {
            new Log(e).Show();
            return false;
        }


        image.ImageID = event.EventID;
        String query1 = "INSERT INTO %s (url,image_type,image_id,created_at) VALUES(?,?,?,?) RETURNING image_id;";

        try{
            ResultSet result1 = this.Query(
                String.format(query1,Image.TableName()),(ParamSetter)(statement) -> {
                    statement.setString(1, image.Url);
                    statement.setString(2, image.ImageType);
                    statement.setString(3, image.ImageID);
                    statement.setTimestamp(4, image.CreatedAt);
                });
                if (result1.next()) {
                    if (result1.getString("image_id").contentEquals("")) {
                        return false;
                    }
                }
        }
        catch (SQLException e) {
            new Log(e).Show();
            return false;
        }
        return true;
    }

    public Repository.Error Update(Event event, Image image) {
        try{
            String query = "UPDATE %s SET title = ?, content = ?, updated_at = ? WHERE event_id = ? RETURNING event_id;";
            ResultSet resultSet = this.Query(String.format(query,Event.TableName()),
            (ParamSetter) (statement) -> {
                statement.setString(1, event.Title);
                statement.setString(2, event.Content);
                statement.setTimestamp(3, event.Updated);
                statement.setString(4, event.EventID);
            });
            if (resultSet.next()) {
                if (!resultSet.getString("event_id").contentEquals(event.EventID)) {
                    return Repository.Error.CanNotUpdate();
                }
            }
        }
        catch(SQLException e){
            new Log(e).Show();
            return new Repository.Error(e.getMessage(), e.getSQLState());
        }

        try{
            String query1 = "UPDATE %s SET url = ?, updated_at = ? WHERE image_id = ?;";
            ResultSet resultSet = this.Query(String.format(query1,Image.TableName()),
            (ParamSetter) (statement) -> {
                statement.setString(1, image.Url);
                statement.setTimestamp(2, image.Updated);
                statement.setString(3, event.EventID);
            });
            if (resultSet.next()) {
                if (!resultSet.getString("image_id").contentEquals(image.ImageID)) {
                    return Repository.Error.CanNotUpdate();
                }
            }
        }
        catch(SQLException e){
            new Log(e).Show();
            return new Repository.Error(e.getMessage(), e.getSQLState());
        }
        return null;
    }

    public boolean Delete(Event event, Image image) {
        try{
            String query = "DELETE FROM %s WHERE event_id = ? returning event_id;";
            ResultSet result = this.Query(String.format(query,Event.TableName()),
            (ParamSetter) (statement) -> {
                statement.setString(1, event.EventID);
            });
            if (result.next()) {
                if (result.getString("event_id").contentEquals("")) {
                    return false;
                }
            }
        }
        catch (SQLException e) {
            new Log(e).Show();
            return false;
        }

        try{
            String query1 = "DELETE FROM %s WHERE image_id = ? returning image_id;";
            ResultSet result = this.Query(String.format(query1,Image.TableName()),
            (ParamSetter) (statement) -> {
                statement.setString(1, event.EventID);
            });
            if (result.next()) {
                if (result.getString("image_id").contentEquals("")) {
                    return false;
                }
            }
        }
        catch (SQLException e) {
            new Log(e).Show();
            return false;
        }
        return true;
    }
}
