package com.back.petHist.model.Usuario;

public class UsuarioRecuperarResponse {
    int nIdUsuario;
    String cNombre;
    String cLogin;
    String cCorreo;
    int nIdTipoUsuario;
    String cNombreTipo;
    String password;
    public int getnIdUsuario() {
        return nIdUsuario;
    }

    public void setnIdUsuario(int nIdUsuario) {
        this.nIdUsuario = nIdUsuario;
    }

    public String getcNombre() {
        return cNombre;
    }

    public void setcNombre(String cNombre) {
        this.cNombre = cNombre;
    }

    public String getcLogin() {
        return cLogin;
    }

    public void setcLogin(String cLogin) {
        this.cLogin = cLogin;
    }

    public String getcCorreo() {
        return cCorreo;
    }

    public void setcCorreo(String cCorreo) {
        this.cCorreo = cCorreo;
    }

    public int getnIdTipoUsuario() {
        return nIdTipoUsuario;
    }

    public void setnIdTipoUsuario(int nIdTipoUsuario) {
        this.nIdTipoUsuario = nIdTipoUsuario;
    }

    public String getcNombreTipo() {
        return cNombreTipo;
    }

    public void setcNombreTipo(String cNombreTipo) {
        this.cNombreTipo = cNombreTipo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
