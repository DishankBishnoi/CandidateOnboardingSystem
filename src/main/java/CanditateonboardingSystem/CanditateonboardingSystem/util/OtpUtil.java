package CanditateonboardingSystem.CanditateonboardingSystem.util;

import CanditateonboardingSystem.CanditateonboardingSystem.Repository.UserDetailsRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@AllArgsConstructor
public class OtpUtil {
    private final String SECRET_KEY = "otp-token-secret-key-for-extra-safety-123456789";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());



    public String generateToken(String email,long minutesToExpire){
    return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(Date.from(Instant.now().plus(minutesToExpire, ChronoUnit.MINUTES)))
            .signWith(key,SignatureAlgorithm.HS256)
            .compact();
    }

    public String validateAndGetEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
