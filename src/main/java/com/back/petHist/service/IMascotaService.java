package com.back.petHist.service;

import com.back.petHist.model.MantenimientoResponseModel;
import com.back.petHist.model.Mascota.MascotaListRequest;
import com.back.petHist.model.Mascota.MascotaListResponse;
import com.back.petHist.model.Mascota.MascotaRequest;

import java.util.List;

public interface IMascotaService {
    public MantenimientoResponseModel mantenimientoMascota(MascotaRequest mascotaRequest);
    public List<MascotaListResponse> mascotaLista(MascotaListRequest mascotaListRequest);

}
