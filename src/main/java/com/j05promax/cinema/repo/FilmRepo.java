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

    public ArrayList<Film> GetAll() throws SQLException {
        ArrayList<Film> films = new ArrayList<Film>();

        String query = "select films.film_id, films.name, films.details, films.price, films.status, films.duration, films.created_at, films.updated_at, images.url from films left join images on images.image_id = films.film_id and images.image_type = 'films'";
        System.out.println(query);

        ResultSet result = this.Query(String.format(query, Film.TableName()), (ParamSetter)(statement) -> {});

        while (result.next()) {
            films.add(new Film().FromResultSet(result));
        }

        this.Close();
        return films;
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
