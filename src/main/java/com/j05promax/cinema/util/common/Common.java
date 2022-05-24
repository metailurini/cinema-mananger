package com.j05promax.cinema.util.common;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.j05promax.cinema.util.common.mail.EmailEngine;
import com.j05promax.cinema.util.common.mail.GmailEngine;
import com.j05promax.cinema.util.log.Log;

public class Common {
    private static Common single_instance = null;
    public static Integer TokenExpired = 3600 * 3; // 3 hours
    public static Integer LenCodeRecoveryPassword = 6; // 7 days
    public static Integer CodeExpiredTime = 15 * 60; // 7 days

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

    public String GetUID() {
        String currentTime = String.valueOf((System.currentTimeMillis() + Math.random() + Math.random()) / 100000L),
                mess = currentTime + currentTime + currentTime + currentTime,
                hashtext = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(mess.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
        } catch (NoSuchAlgorithmException e) {
        }

        return hashtext.toUpperCase();
    }

    public String getRandomCode() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(Common.LenCodeRecoveryPassword);

        for (int i = 0; i < Common.LenCodeRecoveryPassword; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());

            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }

    public Date parseWithFormat(String datetime, String format) {
        Date date = new Date();
        DateFormat formatter = new SimpleDateFormat(format);

        try {
            date = formatter.parse(datetime);
        } catch (java.text.ParseException e) {
            new Log(e).Show();
        }

        return date;
    }
}
