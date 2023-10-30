package com.back.petHist.service;

import com.back.petHist.model.Cita.*;
import com.back.petHist.model.MantenimientoResponseModel;

import java.util.List;

public interface ICitaService {
    public List<HorarioFechaListResponse> horarioFecha(HorarioFechaRequest horarioFechaRequest);
    public MantenimientoResponseModel mantenimientoCita(CitaRequest citaRequest);
    public List<CitaListResponse>  citaLista(CitaListRequest citaListRequest);
}
