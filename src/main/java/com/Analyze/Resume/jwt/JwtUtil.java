package com.Analyze.Resume.jwt;

public class JwtUtil {

    private JwtUtil() {
    }

    public static final String SECRET_KEY =
            "mysecretkeymysecretkeymysecretkeymysecretkey";

    public static final long EXPIRATION_TIME =
            1000 * 60 * 60 * 24; // 24 Hours

}