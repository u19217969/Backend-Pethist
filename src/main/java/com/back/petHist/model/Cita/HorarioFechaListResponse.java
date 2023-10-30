package com.back.petHist.model.Cita;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class HorarioFechaListResponse {
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date fecha;
    String dia;
    String nombreDia;
    List<HorarioHoraListResponse> horario;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getNombreDia() {
        return nombreDia;
    }

    public void setNombreDia(String nombreDia) {
        this.nombreDia = nombreDia;
    }

    public List<HorarioHoraListResponse> getHorario() {
        return horario;
    }

    public void setHorario(List<HorarioHoraListResponse> horario) {
        this.horario = horario;
    }
}
