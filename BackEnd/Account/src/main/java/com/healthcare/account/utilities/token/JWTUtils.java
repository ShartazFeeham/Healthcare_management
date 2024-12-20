package com.healthcare.account.utilities.token;

import com.healthcare.account.utilities.constants.AppConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class JWTUtils {
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = AppConstants.TOKEN_ALPHABETS;
    public static Boolean hasTokenExpired(String token){
        Claims claims = Jwts.parser().setSigningKey(AppConstants.TOKEN_SECRET).parseClaimsJws(token).getBody();
        Date tokenExpirationDate = claims.getExpiration();
        Date today = new Date();
        return tokenExpirationDate.before(today);
    }

    public static String generateInternalToken(){
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_INTERNAL");
        return Jwts.builder()
                .setSubject("INTERNAL")
                .claim("roles", roles)
                .setExpiration(new Date(System.currentTimeMillis() + AppConstants.INTERNAL_TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, AppConstants.TOKEN_SECRET)
                .compact();
    }

    public static String generateToken(String id, List<String> roles){
        return Jwts.builder()
                .setSubject(id)
                .claim("roles", roles)
                .setExpiration(new Date(System.currentTimeMillis()+ AppConstants.TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, AppConstants.TOKEN_SECRET)
                .compact();
    }

    public static String generateUserID(int length){
        return generateRandomString(length);
    }

    private static String generateRandomString(int length){
        StringBuilder returnValue = new StringBuilder(length);
        for (int i = 0;i<length;i++)
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        return new String(returnValue);
    }

    public static String extractUser(String token) {
        return Jwts.parser().setSigningKey(AppConstants.TOKEN_SECRET).parseClaimsJws(token).getBody().getSubject();
    }

    public static List<String> extractUserRoles(String token) {
        Claims claims = Jwts.parser().setSigningKey(AppConstants.TOKEN_SECRET).parseClaimsJws(token).getBody();
        @SuppressWarnings("unchecked") // Suppress warning for this specific cast
        List<String> roles = (List<String>) claims.get("roles");
        return roles;
    }
}