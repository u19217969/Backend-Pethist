package com.back.petHist.model.Acceso;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioAccesoHijoRequest {
    @JsonProperty("idUsuario")
    int nIdUsuario;
    @JsonProperty("idMenu")
    int nIdMenu;
}
