package com.edwin.clinic.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    // Clave segura en Base64 (copiada desde consola)
    private static final String SECRET = "VYcX36DlFDU41HcX6IhdIX3JPNyFnkNwNDfANT1GNmw=";
    // Clave secreta decodificada
    private final SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET));

    public String extractUsername(String token){

        return extractClamis(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClamis(token, Claims::getExpiration);
    }

    public <T> T extractClamis(String token, Function<Claims,T> claimsResolver){

        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username, String role){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role",role);
        return createToken(claims, username);
    }

/*    private String createToken(Map<String,Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS256)
                .compact();

    }*/
private String createToken(Map<String, Object> claims, String subject) {
    return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
            .signWith(key)
            .compact();
}

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username= extractUsername(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    @PostConstruct
    public void init() {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String encodedKey = Encoders.BASE64.encode(key.getEncoded());
        System.out.println("🔐 Llave secreta segura: " + encodedKey);
    }


}