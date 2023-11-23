package com.back.petHist.model.Acceso;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioAccesoHijoResponse {

    @JsonProperty("idMenuHijo")
    int nIdMenuHijo;
    @JsonProperty("nombre")
    String cNombre;
    @JsonProperty("icono")
    String cIcono;
    @JsonProperty("url")
    String cUrl;
    @JsonProperty("orden")
    int nOrden;
    @JsonProperty("acceso")
    Boolean flagAcceso;
    Boolean ver;
    Boolean registrar;
    Boolean actualizar;
    Boolean eliminar;
    Boolean procesar;

    public Boolean getProcesar() {
        return procesar;
    }

    public void setProcesar(Boolean procesar) {
        this.procesar = procesar;
    }

    public int getnIdMenuHijo() {
        return nIdMenuHijo;
    }

    public void setnIdMenuHijo(int nIdMenuHijo) {
        this.nIdMenuHijo = nIdMenuHijo;
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

    public String getcUrl() {
        return cUrl;
    }

    public void setcUrl(String cUrl) {
        this.cUrl = cUrl;
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

    public Boolean getVer() {
        return ver;
    }

    public void setVer(Boolean ver) {
        this.ver = ver;
    }

    public Boolean getRegistrar() {
        return registrar;
    }

    public void setRegistrar(Boolean registrar) {
        this.registrar = registrar;
    }

    public Boolean getActualizar() {
        return actualizar;
    }

    public void setActualizar(Boolean actualizar) {
        this.actualizar = actualizar;
    }

    public Boolean getEliminar() {
        return eliminar;
    }

    public void setEliminar(Boolean eliminar) {
        this.eliminar = eliminar;
    }
}
