package com.back.petHist.service;

import com.back.petHist.model.Maestro.MaestroResponseModel;

import java.util.List;

public interface IMaestroService {
    public List<MaestroResponseModel> listarMaestro(int parametro);
}
