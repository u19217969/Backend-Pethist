package com.back.petHist.repository;

import com.back.petHist.model.Maestro.MaestroResponseModel;
import com.back.petHist.model.Maestro.TablaRequestModel;

import java.util.List;

public interface ITablaRepository {
    public List<MaestroResponseModel> listarTabla(TablaRequestModel tablaRequestModel);
}
