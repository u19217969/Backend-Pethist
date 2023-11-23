package com.back.petHist.model.Mascota;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class MascotaListResponse {
    int idMascota;
    int idGeneroMascota;
    String nombreGenero;
    int idTipoMascota;
    String nombreTipo;
    String nombreMascota;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date fechaNacimiento;
    int idUsuario;
    String nombreUsuario;
    String colorMascota;
    int estado;
    int totalRegistros;
}
