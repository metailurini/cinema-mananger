package com.j05promax.cinema.util.common;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptWrapper {
    private static String salt = BCrypt.gensalt();

    public String HashPassword(String password) {
        return BCrypt.hashpw(password, salt);
    }

    public boolean CheckPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
