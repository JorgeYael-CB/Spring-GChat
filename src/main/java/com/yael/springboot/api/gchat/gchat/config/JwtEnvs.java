package com.yael.springboot.api.gchat.gchat.config;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;



public abstract class JwtEnvs {
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final Date DATE_EXPIRE = new Date(System.currentTimeMillis() + 3600000);
    public static final String CONTENT_TYPE = "application/json";
}
