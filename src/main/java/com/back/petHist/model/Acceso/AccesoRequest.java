package com.back.petHist.model.Acceso;

import java.util.List;

public class AccesoRequest {
    int idUsuario;
    int idMenu;
    boolean ver;
    boolean registrar;
    boolean actualizar;
    boolean eliminar;
    boolean acceso;
    boolean procesar;
    String creaUsuario;
    String modificaUsuario;

    List<AccesoHijoRequest> usuarioAccesoHijo;

    public boolean isProcesar() {
        return procesar;
    }

    public void setProcesar(boolean procesar) {
        this.procesar = procesar;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public boolean isVer() {
        return ver;
    }

    public void setVer(boolean ver) {
        this.ver = ver;
    }

    public boolean isRegistrar() {
        return registrar;
    }

    public void setRegistrar(boolean registrar) {
        this.registrar = registrar;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    public boolean isEliminar() {
        return eliminar;
    }

    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }

    public boolean isAcceso() {
        return acceso;
    }

    public void setAcceso(boolean acceso) {
        this.acceso = acceso;
    }

    public String getCreaUsuario() {
        return creaUsuario;
    }

    public void setCreaUsuario(String creaUsuario) {
        this.creaUsuario = creaUsuario;
    }

    public String getModificaUsuario() {
        return modificaUsuario;
    }

    public void setModificaUsuario(String modificaUsuario) {
        this.modificaUsuario = modificaUsuario;
    }

    public List<AccesoHijoRequest> getUsuarioAccesoHijo() {
        return usuarioAccesoHijo;
    }

    public void setUsuarioAccesoHijo(List<AccesoHijoRequest> usuarioAccesoHijo) {
        this.usuarioAccesoHijo = usuarioAccesoHijo;
    }
}
