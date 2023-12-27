package com.shoes.security.jwt;

import com.shoes.security.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
@NoArgsConstructor
public class JwtUtils {
    // Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
    @Value("${bezkoder.app.jwtSecret}")
    private String jwtSecret;
    //Thời gian có hiệu lực của chuỗi jwt
    @Value("${bezkoder.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    // Tạo ra jwt từ thông tin user
    public String generateJwtToken(Authentication authentication){
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        String generatedToken = Jwts
                .builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(getSignInkey(), SignatureAlgorithm.HS512)
                .compact();
        log.info("Generated Token: {}", generatedToken);
        return generatedToken;
    }

    private Key getSignInkey(){
//        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
//        return Keys.hmacShaKeyFor(keyBytes);
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // Lấy thông tin user từ jwt
    public String getUserNameFromJwtToken(String token){
        return Jwts.parserBuilder().setSigningKey(getSignInkey()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            log.info("authTolen: {}", authToken);
//            Jwts.parserBuilder().setSigningKey(getSignInkey()).build().parseClaimsJws(authToken);
//            return true;
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(getSignInkey()).build().parseClaimsJws(authToken);
            log.info("Decoded Token: {}", claims.getBody().toString());
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
