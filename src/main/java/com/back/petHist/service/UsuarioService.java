package com.back.petHist.service;

import com.back.petHist.model.MantenimientoResponseModel;
import com.back.petHist.model.Mascota.MascotaListResponse;
import com.back.petHist.model.Usuario.*;
import com.back.petHist.model.UsuarioModel;
import com.back.petHist.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService{
    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Override
    public UsuarioListResponse buscarUsuario(UsuarioFindRequest usuarioFindRequest) {
        UsuarioListResponse  usuarioListResponse;
        try {
            usuarioListResponse=iUsuarioRepository.buscarUsuario(usuarioFindRequest);
        }catch (Exception ex){
            throw ex;
        }
        return usuarioListResponse;
    }

    @Override
    public MantenimientoResponseModel mantenimientoUsuario(UsuarioRequest usuarioRequest) {
        MantenimientoResponseModel mantenimientoResponseModel;
        try {
            mantenimientoResponseModel=iUsuarioRepository.mantenimientoUsuario(usuarioRequest);
        }catch (Exception ex){
            throw ex;
        }
        return mantenimientoResponseModel;
    }

    @Override
    public List<UsuarioListResponse> usuarioLista(UsuarioListRequest usuarioListRequest) {
        List<UsuarioListResponse>  usuarioListResponse;
        try {
            usuarioListResponse=iUsuarioRepository.usuarioLista(usuarioListRequest);
        }catch (Exception ex){
            throw ex;
        }
        return usuarioListResponse;
    }

    @Override
    public Optional<UsuarioRecuperarResponse> usuarioLoginResponse(String cCorreo) {
        Optional<UsuarioRecuperarResponse> usuarioLoginResponse;
        try {
            usuarioLoginResponse=iUsuarioRepository.usuarioLoginResponse(cCorreo);
        }catch (Exception ex){
            throw ex;
        }
        return usuarioLoginResponse;
    }

    @Override
    public int save(UsuarioModel usuario) {
        return 0;
    }

    @Override
    public int update(UsuarioModel usuario) {
        return 0;
    }

    @Override
    public int deleteById(int idUsuario) {
        return 0;
    }
}
