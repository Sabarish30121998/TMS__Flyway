package com.example.TMS.Utils;

import com.example.TMS.EntityORModel.Role;
import com.example.TMS.EntityORModel.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class JwtUtil {

    public static String generateToken(String subject, long ownerId, List<Role> roles, String userName) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .claim("ownerId", ownerId)
                .claim("Roles", roles)
                .claim("userName", userName).setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, "secret");


        return builder.compact();
    }



    public String getJwtFromRequest(HttpServletRequest request) {
            String bearerToken = request.getHeader("Authorization");
            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
                return bearerToken.substring(7, bearerToken.length());
            }
            return null;
    }


    public boolean validateToken(String authToken,String signingKey) {
        try {
            Jwts.parser().setSigningKey("secret").parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static String getUserNameFromJWTs(String token, String signingKey) {
        Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
        return String.valueOf(claims.get("userName"));
    }

    public static String getIdFromJWTs(String token, String signingKey) {
        Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
        return String.valueOf(claims.get("ownerId"));
    }


}
