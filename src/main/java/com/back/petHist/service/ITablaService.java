package com.back.petHist.service;

import com.back.petHist.model.Maestro.MaestroResponseModel;
import com.back.petHist.model.Maestro.TablaRequestModel;

import java.util.List;

public interface ITablaService {
    public List<MaestroResponseModel> listarTabla(TablaRequestModel tablaRequestModel);
}
