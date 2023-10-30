package com.back.petHist.service;

import com.back.petHist.model.MantenimientoResponseModel;
import com.back.petHist.model.Usuario.*;
import com.back.petHist.model.UsuarioModel;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    public UsuarioListResponse buscarUsuario(UsuarioFindRequest usuarioFindRequest);
    public MantenimientoResponseModel mantenimientoUsuario(UsuarioRequest usuarioRequest);
    public List<UsuarioListResponse>  usuarioLista(UsuarioListRequest usuarioListRequest);
    Optional<UsuarioRecuperarResponse> usuarioLoginResponse(String cCorreo);
    public int save( UsuarioModel usuario);
    public int update( UsuarioModel usuario);
    public int deleteById( int idUsuario);
}
