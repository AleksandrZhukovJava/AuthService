package me.zhukov.authservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import me.zhukov.authservice.security.dto.UserSecurityDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.jsonwebtoken.io.Decoders.BASE64;
import static java.time.temporal.ChronoUnit.MINUTES;

@Component
public class JwtService {
    @Value("${jwt.expiration}")
    private int expirationTime;
    private final Key jwtKey;
    private final JwtParser parser;

    public JwtService(@Value("${jwt.secret}") String jwtSecret) {
        this.jwtKey = Keys.hmacShaKeyFor(BASE64.decode(jwtSecret));
        this.parser = Jwts.parserBuilder()
                .setSigningKey(jwtKey)
                .build();
    }


    public String generateToken(UserSecurityDto userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userDetails.getUser().getId().toString());
        claims.put("role", userDetails.getUser().getRole().name());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(expirationTime, MINUTES)))
                .signWith(jwtKey, SignatureAlgorithm.HS256).compact();
    }

    public String extractUserName(String token) {
        return getClaimsByToken(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return Objects.equals(userName, userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return getClaimsByToken(token)
                .getExpiration()
                .before(new Date());
    }

    private Claims getClaimsByToken(String token) {
        return parser.parseClaimsJws(token)
                .getBody();
    }
}

