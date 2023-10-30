package com.back.petHist.model.Acceso;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UsuarioAccesoResponse {
    @JsonProperty("idMenu")
    int nIdMenu;
    @JsonProperty("nombre")
    String cNombre;
    @JsonProperty("icono")
    String cIcono;
    @JsonProperty("orden")
    int nOrden;
    @JsonProperty("seleccion")
    boolean seleccion;
    @JsonProperty("acceso")
    Boolean flagAcceso;
    List<UsuarioAccesoHijoResponse> usuarioAccesoHijo;

    public int getnIdMenu() {
        return nIdMenu;
    }

    public void setnIdMenu(int nIdMenu) {
        this.nIdMenu = nIdMenu;
    }

    public String getcNombre() {
        return cNombre;
    }

    public void setcNombre(String cNombre) {
        this.cNombre = cNombre;
    }

    public String getcIcono() {
        return cIcono;
    }

    public void setcIcono(String cIcono) {
        this.cIcono = cIcono;
    }

    public int getnOrden() {
        return nOrden;
    }

    public void setnOrden(int nOrden) {
        this.nOrden = nOrden;
    }

    public Boolean getFlagAcceso() {
        return flagAcceso;
    }

    public void setFlagAcceso(Boolean flagAcceso) {
        this.flagAcceso = flagAcceso;
    }

    public List<UsuarioAccesoHijoResponse> getUsuarioAccesoHijo() {
        return usuarioAccesoHijo;
    }

    public void setUsuarioAccesoHijo(List<UsuarioAccesoHijoResponse> usuarioAccesoHijo) {
        this.usuarioAccesoHijo = usuarioAccesoHijo;
    }
}
