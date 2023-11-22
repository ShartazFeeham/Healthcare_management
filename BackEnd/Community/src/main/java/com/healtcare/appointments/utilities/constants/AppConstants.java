package com.healthcare.notification.utilities.constants;

import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.function.Function;

public class AppConstants {
    public static final String TOKEN_SECRET = "PAKHI_PAKA_PEPE_KHAY";
    public static final String PUSH_URL = "http://localhost:5300/v1/push-notification/send";
    public static final String EMAIL_URL = "http://localhost:5300/v1/email/send";
    public static final String SMS_URL = "http://localhost:5300//v1/sms/send";
    public static final String INTERNAL_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJJTlRFUk5BTCIsInJvbGVzIjpbIlJPTEVfSU5URVJOQUwiXSwiZXhwIjoyMDE1MjE2Mzg0fQ.GpRoQRcjHJjk6DHaT-qpV0dkvJF_7GGsiaq6pTmc_Fk";

    public static final String NOTIFICATION_ENDPOINT = "http://localhost:7600/notifications";
}