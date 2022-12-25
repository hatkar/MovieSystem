package com.hatmani.gatway;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
@Component
public class JwtUtil {
    @Value("${app.jwtSecret}")
    private String secret;

    private Key key;
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims getAllClaimsFromToken(String token) {
       // Jwts.parser().setSigningKey(secret)
        //        .parseClaimsJws(token).getBody().getSubject();
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
    }

    public boolean isInvalid(String token) {
        return !this.validateJwtToken(token);
    }

    public boolean validateJwtToken(String authToken)
    {
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            //Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        }catch(SignatureException e)
        {
            logger.error("Invalid JWT signature :{}",e.getMessage());

        }catch(MalformedJwtException e)
        {
            logger.error("Invalid JWT Token :{}",e.getMessage());
        }catch(ExpiredJwtException e)
        {
            logger.error("JWT token is expired :{}",e.getMessage());
        }catch(UnsupportedJwtException e)
        {
            logger.error("JWT Token Unsuported :{}",e.getMessage());
        }
        catch(IllegalArgumentException e)
        {
            logger.error("JWT claims string is empty :{}",e.getMessage());
        }
        return false;
    }

    public boolean hasRole(String token, String role) {
        String roleIntoken="";
        Claims claims = getAllClaimsFromToken(token);
        roleIntoken=String.valueOf(claims.get("role"));
        return role.equalsIgnoreCase(roleIntoken);
    }
}
