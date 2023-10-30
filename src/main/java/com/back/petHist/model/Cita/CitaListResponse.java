package com.back.petHist.model.Cita;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class CitaListResponse {
    int idCita;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date fechaSolicitud;

    @JsonFormat(pattern = "yyyy-MM-dd")
    Date fechaCita;
    int idCliente;
    String nombreCliente;
    int idDoctor;
    String nombreDoctor;
    int idMascota;
    String nombreMascota;
    String horaCita;
    String motivo;
    boolean estado;
    int totalRegistros;
}
