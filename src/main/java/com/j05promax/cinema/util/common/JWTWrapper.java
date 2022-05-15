package com.j05promax.cinema.util.common;

import java.sql.Date;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

public class JWTWrapper {
    private Algorithm algorithm;
    private String secret;
    public Integer expireDuration;
    public String issuser;
    public String audience;
    public String subject;

    protected JWTWrapper(
        String secret, Integer expireDuration,
        String issuser, String audience,
        String subject
    ) {
        this.secret = secret;
        this.expireDuration = expireDuration;
		this.algorithm = Algorithm.HMAC256(this.secret);
        this.issuser = issuser;
        this.audience = audience;
        this.subject = subject;
    }

    public String Encode(Map<String, ?> claim) throws JWTCreationException {
        String token = "";

        JWTCreator.Builder builder = JWT.create();
        for(String key: claim.keySet()) {
            builder = builder.withClaim(key, (Map<String, ?>)claim.get(key));
        }

        token = builder
                .withIssuer(this.issuser)
                .withAudience(this.audience)
                .withSubject(this.subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + this.expireDuration))
                .sign(this.algorithm);
        return token;
    }

    public Map<String, ?> Decode(String token) throws JWTVerificationException {
        Map<String, ?> map = JWT.require(this.algorithm)
                .withIssuer(this.issuser)
                .withAudience(this.audience)
                .withSubject(this.subject)
                .build()
                .verify(token)
                .getClaims();

        return map;
    }
}