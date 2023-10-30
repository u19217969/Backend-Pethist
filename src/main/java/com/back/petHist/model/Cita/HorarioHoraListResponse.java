package com.back.petHist.model.Cita;

import lombok.Data;
@Data
public class HorarioHoraListResponse {
    String hora;
    boolean disponibilidad;
    boolean miCita;

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public boolean isMiCita() {
        return miCita;
    }

    public void setMiCita(boolean miCita) {
        this.miCita = miCita;
    }
}
