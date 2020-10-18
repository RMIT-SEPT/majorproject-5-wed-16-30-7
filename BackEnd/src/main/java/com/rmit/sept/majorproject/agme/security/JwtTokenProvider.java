package com.rmit.sept.majorproject.agme.security;

import com.rmit.sept.majorproject.agme.model.Person;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenProvider {
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public String generateToken(Authentication authentication)
    {
        Person person = (Person)authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());

        Date expiry = new Date(now.getTime() + 30_000);

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", person.getId());
        claims.put("username", person.getUsername());
        claims.put("fullName", person.getName());


        return Jwts.builder()
                .setSubject(person.getId().toString())
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key)
                .compact();

    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        } catch (Exception ex)
        {
            System.out.println("Invalid");
        }

        return false;
    }

    public Long getUserIdFromJWT(String token){
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        String id = (String)claims.get("id");

        return Long.parseLong(id);
    }

}
