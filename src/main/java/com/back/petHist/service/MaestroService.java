package com.back.petHist.service;

import com.back.petHist.model.Maestro.MaestroResponseModel;
import com.back.petHist.repository.IMaestroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaestroService implements IMaestroService{
    @Autowired
    private IMaestroRepository iMaestroRepository;

    @Override
    public List<MaestroResponseModel> listarMaestro(int parametro) {
        List<MaestroResponseModel> listMaestroResponseModel;
        try {
            listMaestroResponseModel=iMaestroRepository.listarMaestro(parametro);
        }catch (Exception ex){
            throw ex;
        }
        return listMaestroResponseModel;
    }
}
