package com.j05promax.cinema.repo;


import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.j05promax.cinema.entity.Film;
import com.j05promax.cinema.util.db.DBConnection;

public class FilmRepo extends Repository {

    public FilmRepo(DBConnection conn) {
        super(conn);
    }

    public ArrayList<Film> GetAll(String search) throws SQLException {
        ArrayList<Film> films = new ArrayList<Film>();

        String query = (
            " SELECT " +
            " 	films.film_id, " +
            " 	films.name, " +
            " 	films.details, " +
            " 	films.price, " +
            " 	films.status, " +
            " 	films.category, " +
            " 	films.duration, " +
            " 	films.created_at, " +
            " 	films.updated_at, " +
            " 	images.url " +
            " from " +
            " 	films " +
            " LEFT JOIN images ON " +
            " 	images.image_id = films.film_id AND " +
            " 	images.image_type = 'films' " +
            " WHERE (LOWER(name) LIKE ? OR LOWER(category) LIKE ?) ");

        ResultSet result = this.Query(
            String.format(query, Film.TableName()),
            (ParamSetter)(statement) -> {
                statement.setString(1, ("%" + search + "%").toLowerCase());
                statement.setString(2, ("%" + search + "%").toLowerCase());
            }
        );

        while (result.next()) {
            films.add(new Film().FromResultSet(result));
        }

        this.Close();
        return films;
    }

    public int CountFilmActive() throws SQLException{
        int count_film_active = 0;
        String query = "SELECT COUNT(*) AS counted FROM films WHERE status LIKE N'%open%'";
        ResultSet result = this.Query(query, (ParamSetter)(statement) -> {});
        if(result.next()){
            count_film_active = result.getInt("counted");
        }
        this.Close();
        return count_film_active;
    }

    public Film GetByID(String id) {
        return new Film();
    }

    public boolean Create(String id) {
        return true;
    }

    public boolean Update(String id, Film film) {
        return true;
    }

    public boolean Delete(String id) {
        return true;
    }
}
