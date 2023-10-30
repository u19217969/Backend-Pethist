package com.back.petHist.repository;

import com.back.petHist.model.Cita.*;
import com.back.petHist.model.MantenimientoResponseModel;

import java.util.List;

public interface ICitaRepository {
    public List<HorarioFechaListResponse> horarioFecha(HorarioFechaRequest horarioFechaRequest);
    public MantenimientoResponseModel mantenimientoCita(CitaRequest citaRequest);
    public List<CitaListResponse>  citaLista(CitaListRequest citaListRequest);
}
