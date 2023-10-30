package com.back.petHist.security;

import com.back.petHist.model.Usuario.UsuarioLoginResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {
    private final static String ACCESS_TOKEN_SECRET= "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.utcjcgtC-ypEbsgNZ7_hi53YurZHV6rZ3AQe7YiJ8hs";
    private final static Long ACCESS_TOKEN_VALIDITY_SECOND= 2_592_00L;

    public static String createToken(UsuarioLoginResponse usuarioLoginResponse){
        long expirationTime=ACCESS_TOKEN_VALIDITY_SECOND * 1_000;
        Date expirateDate= new Date(System.currentTimeMillis()+expirationTime);

        Map<String,Object> extra= new HashMap<>();
        extra.put("idUsuario", usuarioLoginResponse.getnIdUsuario());
        extra.put("nombre", usuarioLoginResponse.getcNombre());
        extra.put("login", usuarioLoginResponse.getcLogin());
        extra.put("correo", usuarioLoginResponse.getcCorreo());
        extra.put("idTipoUsuario", usuarioLoginResponse.getnIdTipoUsuario());
        extra.put("nombreTipo", usuarioLoginResponse.getcNombreTipo());

        return Jwts.builder()
                .setSubject(usuarioLoginResponse.getcCorreo())
                .setExpiration(expirateDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }
    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try {
            Claims claims=Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email=claims.getSubject();

            return new UsernamePasswordAuthenticationToken(email,null, Collections.emptyList());
        }catch (JwtException e){
            return null;
        }
    }
}
