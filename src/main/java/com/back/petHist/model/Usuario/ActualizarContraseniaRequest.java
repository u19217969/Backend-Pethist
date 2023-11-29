package com.back.petHist.model.Usuario;

import lombok.Data;

@Data
public class ActualizarContraseniaRequest {
    public int idUsuario;
    public String contrasenia;
}