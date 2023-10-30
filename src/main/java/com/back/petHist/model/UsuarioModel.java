package com.back.petHist.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UsuarioModel{
    @JsonProperty("idUsuario")
    int nIdUsuario;
    @JsonProperty("idTipoDocumento")
    int nIdTipoDocumento;
    @JsonProperty("nombreTipoDocumento")
    String cNombreTipoDocumento;
    @JsonProperty("idTipoUsuario")
    int nIdTipoUsuario ;
    @JsonProperty("nombreTipoUsuario")
    String cNombreTipoUsuario;
    @JsonProperty("nombre")
    String cNombre;
    @JsonProperty("login")
    String cLogin;
    @JsonProperty("nroDocumento")
    String cNroDocumento;
    @JsonProperty("correo")
    String cCorreo;
    @JsonProperty("estado")
    Boolean bEstado;

}
