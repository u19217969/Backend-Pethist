package com.back.petHist.security;

import com.back.petHist.model.Authentication.AuthCredentials;
import com.back.petHist.model.Usuario.UsuarioLoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        AuthCredentials authCredentials= new AuthCredentials();
        try {
            authCredentials=new ObjectMapper().readValue(request.getReader(),AuthCredentials.class);
        }catch (IOException e){
        }
        UsernamePasswordAuthenticationToken userNamePAT=new UsernamePasswordAuthenticationToken(
                authCredentials.getEmail(),
                authCredentials.getPassword(),
                Collections.emptyList()
        );
        return getAuthenticationManager().authenticate(userNamePAT);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetailsSecurity userDetailsSecurity=(UserDetailsSecurity) authResult.getPrincipal();
        UsuarioLoginResponse usuarioLoginResponse=new UsuarioLoginResponse();
        usuarioLoginResponse.setnIdUsuario(userDetailsSecurity.getIdUsuario());
        usuarioLoginResponse.setcNombre(userDetailsSecurity.getNombre());
        usuarioLoginResponse.setcLogin(userDetailsSecurity.getLogin());
        usuarioLoginResponse.setcCorreo(userDetailsSecurity.getCorreo());
        usuarioLoginResponse.setnIdTipoUsuario(userDetailsSecurity.getIdTipoUsuario());
        usuarioLoginResponse.setcNombreTipo(userDetailsSecurity.getNombreTipo());
        String token=TokenUtils.createToken(usuarioLoginResponse);
        response.addHeader("Authorization","Bearer "+token);
        response.getWriter().flush();

        super.successfulAuthentication(request,response,chain,authResult);
    }
}
