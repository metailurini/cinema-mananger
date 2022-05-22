package com.j05promax.cinema.repo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.j05promax.cinema.util.db.DBConnection;

interface ParamSetter {
    void func(PreparedStatement statement) throws SQLException;
}

public class Repository {
    private DBConnection conn;
    private PreparedStatement statement;
    private ResultSet result;

    public static class Perpage {
        public int maxInPage = 8;
        public int page;

        public Perpage(int page) {
            this.page = page;
        }
    }

    public static class Error {
        public String message;
        public String code;
        public static final String Unique = "23505";

        public Error(String message, String codeString) {
            this.message = message;
            code = codeString;
        }

        public static Error CanNotUpdate() {
            return new Error("không thể cập nhật dữ liệu", "1");
        }
    }

    public Repository(DBConnection connection) {
        conn = connection;
    }

    public ResultSet Query(String queryStatement, ParamSetter paramSetter) throws SQLException {
        statement = conn.Connection().prepareStatement(queryStatement);
        paramSetter.func(statement);
        result = statement.executeQuery();

        return result;
    }

    public void Close() throws SQLException {
        statement.close();
        result.close();
    }

}
