package com.back.petHist.repository;

import com.back.petHist.model.Maestro.MaestroResponseModel;

import java.util.List;


public interface IMaestroRepository {
    public List<MaestroResponseModel> listarMaestro(int parametro);
}
