package com.j05promax.cinema.util.common;

public class Common {
    private static Common single_instance = null;
    public static Integer TokenExpired = 3600 * 3; // 3 hours

    public JWTWrapper JWT;
    public BcryptWrapper Bcrypt;

    private Common() {
        this.JWT = new JWTWrapper(
                "secret",
                Common.TokenExpired * 1000, 
                "shanenoi",
                "audience",
                "subject");
        this.Bcrypt = new BcryptWrapper();
    }

    public static Common getInstance() {
        if (single_instance == null) {
            single_instance = new Common();
        }

        return single_instance;
    }
}
