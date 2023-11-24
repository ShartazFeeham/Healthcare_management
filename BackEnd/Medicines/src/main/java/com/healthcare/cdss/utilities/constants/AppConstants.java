package com.healthcare.cdss.utilities.constants;

public class AppConstants {
    public static final String TOKEN_SECRET = "PAKHI_PAKA_PEPE_KHAY";
    // Setting for 30 days.
    public static final long TOKEN_EXPIRATION_TIME = 1000L * 60L * 60L * 24L * 30L;
    public static final String TOKEN_HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final Integer MAX_LOGIN_ATTEMPTS_LIMIT = 3;
    public static final String TOKEN_ALPHABETS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static final String INTERNAL_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJJTlRFUk5BTCIsInJvbGVzIjpbIlJPTEVfSU5URVJOQUwiXSwiZXhwIjoyMDE1MjE2Mzg0fQ.GpRoQRcjHJjk6DHaT-qpV0dkvJF_7GGsiaq6pTmc_Fk";
    public static final String ACCOUNT_CREATE_ENDPOINT = "http://localhost:5100/account/create-account";
}
