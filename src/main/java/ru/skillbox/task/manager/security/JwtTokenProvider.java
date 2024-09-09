package ru.skillbox.task.manager.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenProvider {
    @Value("${jwt.lifetime}")
    private Integer jwtLifeTime;
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        List<String> rolesList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();
        claims.put("roles", rolesList);

        Date generatedDate = new Date();
        Date expiredDate = new Date(generatedDate.getTime() + jwtLifeTime);
        return Jwts.builder()
                .setIssuedAt(generatedDate)
                .setClaims(claims)
                .setExpiration(expiredDate)
                .setSubject(userDetails.getUsername())
                .signWith(SignatureAlgorithm.ES256, secret)
                .compact();
    }

    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public List<String> getRolesFromToken(String token){
        return getClaimFromToken(token, (Function<Claims, List<String>>) claims ->claims.get("roles", List.class));
    }
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimFunction){
        Claims claims = getAllClaimsFromToken(token);
        return claimFunction.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }
}
