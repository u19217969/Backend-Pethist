package com.back.petHist.model.Usuario;

import lombok.Data;

@Data
public class UsuarioListResponse {
    int idUsuario;
    int idTipoDocumento;
    String nombreTipoDocumento;
    int idTipoUsuario;
    String nombreTipoUsuario;
    String nombreUsuario;
    String login;
    String nroDocumento;
    String correo;
    int estado;
    int totalRegistros;
}
