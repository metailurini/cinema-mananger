package com.j05promax.cinema.repo;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.j05promax.cinema.entity.Admin;
import com.j05promax.cinema.util.db.DBConnection;
import com.j05promax.cinema.util.log.Log;

public class AdminRepo extends Repository {

    public AdminRepo(DBConnection conn) {
        super(conn);
    }

    public ArrayList<Admin> GetAll() throws SQLException {
        ArrayList<Admin> admins = new ArrayList<Admin>();

        String query = "select * from %s";
        ResultSet result = this.Query(String.format(query, Admin.TableName()), (ParamSetter) (statement) -> {
        });

        while (result.next()) {
            admins.add(new Admin().FromResultSet(result));
        }

        this.Close();
        return admins;
    }

    public Admin GetByID(String id) {
        return new Admin();
    }

    public Admin GetByEmail(String email) throws SQLException {
        Admin aAdmin = null;

        String query = "select * from %s where email = ? limit 1";
        ResultSet result = this.Query(
                String.format(query, Admin.TableName()),
                (ParamSetter) (statement) -> {
                    statement.setString(1, email);
                });

        if (result.next()) {
            aAdmin = new Admin().FromResultSet(result);
        }

        return aAdmin;
    }

    public boolean Create(String id) {
        return true;
    }

    public Repository.Error Update(Admin admin) {
        String query = "UPDATE %s set password = ?, sec_code = ?, email = ?, updated_at = ? where admin_id = ? RETURNING admin_id";
        try {
            ResultSet result = this.Query(
                    String.format(query, Admin.TableName()),
                    (ParamSetter) (statement) -> {
                        statement.setString(1, admin.Password);
                        statement.setString(2, admin.SecCode);
                        statement.setString(3, admin.Email);
                        statement.setTimestamp(4, admin.UpdatedAt);
                        statement.setString(5, admin.AdminID);
                    });

            if (result.next()) {
                if (!result.getString("admin_id").contentEquals(admin.AdminID)) {
                    return Repository.Error.CanNotUpdate();
                }
            }
        } catch (SQLException e) {
            new Log(e).Show();
            return new Repository.Error(e.getMessage(), e.getSQLState());
        }

        return null;
    }

    public boolean Delete(String id) {
        return true;
    }
}