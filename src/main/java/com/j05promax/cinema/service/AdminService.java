package com.j05promax.cinema.service;


import java.sql.Timestamp;
import java.util.Date;

import com.j05promax.cinema.entity.Admin;
import com.j05promax.cinema.repo.PostgreSQLRepo;
import com.j05promax.cinema.util.common.Common;
import com.j05promax.cinema.util.common.mail.Mail;
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

    public boolean RequestCodeEmail(Admin admin) {
        Common cm = Common.getInstance();
        PostgreSQLRepo repo = PostgreSQLRepo.getInstance();

        admin.SecCode = cm.getRandomCode();
        admin.UpdatedAt = new Timestamp(new Date().getTime());
        if(repo.Admin.Update(admin) != null) {
            return false;
        }

        System.out.println("code: " + admin.SecCode);

        cm.Gmail.Send(
            new Mail().blank()
            .to("Winx Staff", admin.Email)
            .withSubject("Winx Cinema - Password Recovery")
            .withHTMLText(String.format(
                "LInk: localhost:8080/auth/change-password?code=%s&email=%s",
                admin.SecCode,
                admin.Email
            ))
        );

        return true;
    }
}