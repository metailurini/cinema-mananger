package com.j05promax.cinema.util.common;

import com.j05promax.cinema.util.common.mail.EmailEngine;
import com.j05promax.cinema.util.common.mail.GmailEngine;

public class Common {
    private static Common single_instance = null;
    public static Integer TokenExpired = 3600 * 3; // 3 hours

    public JWTWrapper JWT;
    public BcryptWrapper Bcrypt;
    public EmailEngine Gmail;

    private Common() {
        this.Bcrypt = new BcryptWrapper();
        this.JWT = new JWTWrapper(
                "secret",
                Common.TokenExpired * 1000, 
                "winx-cinema",
                "winx-user",
                "token-validate");

        this.Gmail = new GmailEngine(
                "shanenoi.org@gmail.com",
                "rcxzropoeegnbfmr")
                .sendWithAsync(true)
                .setName("Winx Cinema");
    }

    public static Common getInstance() {
        if (single_instance == null) {
            single_instance = new Common();
        }

        return single_instance;
    }
}
