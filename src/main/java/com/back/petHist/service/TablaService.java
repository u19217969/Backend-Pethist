package com.back.petHist.service;

import com.back.petHist.model.Maestro.MaestroResponseModel;
import com.back.petHist.model.Maestro.TablaRequestModel;
import com.back.petHist.repository.ITablaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TablaService implements ITablaService{
    @Autowired
    private ITablaRepository iTablaRepository;

    @Override
    public List<MaestroResponseModel> listarTabla(TablaRequestModel tablaRequestModel) {
        List<MaestroResponseModel> listTablaResponseModel;
        try {
            listTablaResponseModel=iTablaRepository.listarTabla(tablaRequestModel);
        }catch (Exception ex){
            throw ex;
        }
        return listTablaResponseModel;
    }
}
