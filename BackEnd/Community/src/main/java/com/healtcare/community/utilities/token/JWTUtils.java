package com.healtcare.community.utilities.token;

import com.healthcare.notification.utilities.constants.TokenConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class JWTUtils {
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = TokenConstants.ALPHABETS;

    /**
     * Checks if a given JWT token has expired.
     *
     * @param token The JWT token to check.
     * @return True if the token has expired, false otherwise.
     */
    public static Boolean hasTokenExpired(String token){
        Claims claims = Jwts.parser().setSigningKey(TokenConstants.TOKEN_SECRET).parseClaimsJws(token).getBody();
        Date tokenExpirationDate = claims.getExpiration();
        Date today = new Date();
        return tokenExpirationDate.before(today);
    }

    /**
     * Generates a random user ID of the specified length.
     *
     * @param length The length of the generated user ID.
     * @return The generated user ID.
     */
    public static String generateUserID(int length){
        return generateRandomString(length);
    }

    private static String generateRandomString(int length){
        StringBuilder returnValue = new StringBuilder(length);
        for (int i = 0;i<length;i++)
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        return new String(returnValue);
    }

    /**
     * Extracts the user ID from a JWT token.
     *
     * @param token The JWT token from which to extract the user ID.
     * @return The user ID extracted from the token.
     */
    public static String extractUser(String token) {
        return Jwts.parser().setSigningKey(TokenConstants.TOKEN_SECRET).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Extracts the user roles from a JWT token.
     *
     * @param token The JWT token from which to extract the user roles.
     * @return The list of user roles extracted from the token.
     */
    public static List<String> extractUserRoles(String token) {
        Claims claims = Jwts.parser().setSigningKey(TokenConstants.TOKEN_SECRET).parseClaimsJws(token).getBody();
        @SuppressWarnings("unchecked") // Suppress warning for this specific cast
        List<String> roles = (List<String>) claims.get("roles");
        return roles;
    }
}