package com.back.petHist.security;

import com.back.petHist.model.Usuario.UsuarioRecuperarResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
public class UserDetailsSecurity implements UserDetails {

    private final UsuarioRecuperarResponse usuarioRecuperarResponse;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return usuarioRecuperarResponse.getPassword();
    }

    @Override
    public String getUsername() {
        return usuarioRecuperarResponse.getcCorreo();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getIdUsuario(){
        return usuarioRecuperarResponse.getnIdUsuario();
    }
    public String getNombre(){
        return usuarioRecuperarResponse.getcNombre();
    }

    public String getCorreo() {
        return usuarioRecuperarResponse.getcCorreo();
    }

    public String getLogin() {
        return usuarioRecuperarResponse.getcLogin();
    }

    public int getIdTipoUsuario() {
        return usuarioRecuperarResponse.getnIdTipoUsuario();
    }


    public String getNombreTipo() {
        return usuarioRecuperarResponse.getcNombreTipo();
    }

}
