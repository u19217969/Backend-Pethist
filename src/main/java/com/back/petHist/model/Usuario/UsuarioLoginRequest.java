package com.back.petHist.model.Usuario;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioLoginRequest {
    @JsonProperty("nroDocumento")
    String cNroDocumento;
    @JsonProperty("clave")
    String cClave;

    public String getcNroDocumento() {
        return cNroDocumento;
    }

    public void setcNroDocumento(String cNroDocumento) {
        this.cNroDocumento = cNroDocumento;
    }

    public String getcClave() {
        return cClave;
    }

    public void setcClave(String cClave) {
        this.cClave = cClave;
    }
}
