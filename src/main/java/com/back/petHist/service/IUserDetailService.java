package com.back.petHist.service;

import com.back.petHist.model.Usuario.UsuarioRecuperarResponse;
import com.back.petHist.repository.UsuarioRepository;
import com.back.petHist.security.UserDetailsSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class IUserDetailService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String cCorreo) throws UsernameNotFoundException {
        UsuarioRecuperarResponse usuarioReponse= usuarioRepository
                .usuarioLoginResponse(cCorreo)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con email "+cCorreo+" no existe"));
        return new UserDetailsSecurity(usuarioReponse);
    }


}
