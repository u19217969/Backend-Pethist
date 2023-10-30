package com.back.petHist.model.Acceso;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioAccesoRequest {
    @JsonProperty("idUsuario")
    int nIdUsuario;

    public int getnIdUsuario() {
        return nIdUsuario;
    }

    public void setnIdUsuario(int nIdUsuario) {
        this.nIdUsuario = nIdUsuario;
    }
}
