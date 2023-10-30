package com.back.petHist.service;

import com.back.petHist.model.MantenimientoResponseModel;
import com.back.petHist.model.Mascota.MascotaListRequest;
import com.back.petHist.model.Mascota.MascotaListResponse;
import com.back.petHist.model.Mascota.MascotaRequest;
import com.back.petHist.repository.IMascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaService implements IMascotaService{
    @Autowired
    private IMascotaRepository iMascotaRepository;
    @Override
    public MantenimientoResponseModel mantenimientoMascota(MascotaRequest mascotaRequest) {
        MantenimientoResponseModel mantenimientoResponseModel;
        try {
            mantenimientoResponseModel=iMascotaRepository.mantenimientoMascota(mascotaRequest);
        }catch (Exception ex){
            throw ex;
        }
        return mantenimientoResponseModel;
    }

    @Override
    public List<MascotaListResponse> mascotaLista(MascotaListRequest mascotaListRequest) {
        List<MascotaListResponse>  mascotaListResponse;
        try {
            mascotaListResponse=iMascotaRepository.mascotaLista(mascotaListRequest);
        }catch (Exception ex){
            throw ex;
        }
        return mascotaListResponse;
    }
}
