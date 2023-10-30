package com.back.petHist.service;

import com.back.petHist.model.Acceso.AccesoRequest;
import com.back.petHist.model.Acceso.UsuarioAccesoRequest;
import com.back.petHist.model.Acceso.UsuarioAccesoResponse;
import com.back.petHist.model.MantenimientoResponseModel;

import java.util.List;

public interface IAccesoService {
    public List<UsuarioAccesoResponse> accesoUsuario(UsuarioAccesoRequest usuarioAccesoRequest);
    public MantenimientoResponseModel mantenimientoAcceso(AccesoRequest[]  accesoRequest);
}
