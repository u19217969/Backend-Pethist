package com.back.petHist.model.Notificacion;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class NotificacionListResponse {
    Date fechaSolicitud;
    String nombreDoctor;
    String nombreCliente;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date fechaCita;
    String nombreMascota;
    String horaCita;
    int estado;
    int totalRegistros;
}
