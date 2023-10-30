package com.back.petHist.model.Acceso;

public class AccesoHijoRequest {
    int idUsuario;
    int idMenuHijo;
    boolean ver;
    boolean registrar;
    boolean actualizar;
    boolean eliminar;
    boolean acceso;
    String creaUsuario;
    String modificaUsuario;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdMenuHijo() {
        return idMenuHijo;
    }

    public void setIdMenuHijo(int idMenuHijo) {
        this.idMenuHijo = idMenuHijo;
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
}
