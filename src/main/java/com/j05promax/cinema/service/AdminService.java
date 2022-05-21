package com.j05promax.cinema.service;

import com.j05promax.cinema.entity.Admin;
import com.j05promax.cinema.util.common.Common;
import com.j05promax.cinema.util.log.Log;

public class AdminService {
    public boolean CheckCorrectPassword(Admin admin, String password) {
        Common cm = Common.getInstance();
        boolean status = false;
        try {
            status = cm.Bcrypt.CheckPassword(password, admin.Password);
        } catch (Exception e) {
            new Log(e).Show();
        }
        return status;
    }
}