package com.yael.springboot.api.gchat.gchat.config;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;



public abstract class JwtEnvs {
    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    private static final String PREFIX_TOKEN = "Bearer ";
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final Date DATE_EXPIRE = new Date(System.currentTimeMillis() + 3600000);
    private static final String CONTENT_TYPE = "application/json";


    public static SecretKey getSecretKey() {
        return SECRET_KEY;
    }
    public static String getPrefixToken() {
        return PREFIX_TOKEN;
    }
    public static String getHeaderAuthorization() {
        return HEADER_AUTHORIZATION;
    }
    public static Date getDateExpire() {
        return DATE_EXPIRE;
    }
    public static String getContentType() {
        return CONTENT_TYPE;
    }
}
