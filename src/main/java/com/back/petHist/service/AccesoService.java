package com.back.petHist.service;

import com.back.petHist.model.Acceso.AccesoRequest;
import com.back.petHist.model.Acceso.UsuarioAccesoRequest;
import com.back.petHist.model.Acceso.UsuarioAccesoResponse;
import com.back.petHist.model.MantenimientoResponseModel;
import com.back.petHist.repository.IAccesoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccesoService implements IAccesoService{
    @Autowired
    private IAccesoRepository iAccesoRepository;

    @Override
    public List<UsuarioAccesoResponse> accesoUsuario(UsuarioAccesoRequest usuarioAccesoRequest) {
        List<UsuarioAccesoResponse> listUsuarioAccesoResponse;
        try {
            listUsuarioAccesoResponse=iAccesoRepository.accesoUsuario(usuarioAccesoRequest);
        }catch (Exception ex){
            throw ex;
        }
        return listUsuarioAccesoResponse;
    }

    @Override
    public MantenimientoResponseModel mantenimientoAcceso(AccesoRequest[]  accesoRequest) {
        MantenimientoResponseModel mantenimientoResponseModel;
        try {
            mantenimientoResponseModel=iAccesoRepository.mantenimientoAcceso(accesoRequest);
        }catch (Exception ex){
            throw ex;
        }
        return mantenimientoResponseModel;
    }
}
