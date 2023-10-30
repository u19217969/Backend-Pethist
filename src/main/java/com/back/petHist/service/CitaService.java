package com.back.petHist.service;

import com.back.petHist.model.Cita.*;
import com.back.petHist.model.MantenimientoResponseModel;
import com.back.petHist.repository.ICitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaService implements ICitaService{
    @Autowired
    private ICitaRepository iCitaRepository;
    @Override
    public List<HorarioFechaListResponse> horarioFecha(HorarioFechaRequest horarioFechaRequest) {
        List<HorarioFechaListResponse> listHorarioFechaResponse;
        try {
            listHorarioFechaResponse=iCitaRepository.horarioFecha(horarioFechaRequest);
        }catch (Exception ex){
            throw ex;
        }
        return listHorarioFechaResponse;
    }

    @Override
    public MantenimientoResponseModel mantenimientoCita(CitaRequest citaRequest) {
        MantenimientoResponseModel mantenimientoResponseModel;
        try {
            mantenimientoResponseModel=iCitaRepository.mantenimientoCita(citaRequest);
        }catch (Exception ex){
            throw ex;
        }
        return mantenimientoResponseModel;
    }

    @Override
    public List<CitaListResponse> citaLista(CitaListRequest citaListRequest) {
        List<CitaListResponse>  citaListResponse;
        try {
            citaListResponse=iCitaRepository.citaLista(citaListRequest);
        }catch (Exception ex){
            throw ex;
        }
        return citaListResponse;
    }
}
