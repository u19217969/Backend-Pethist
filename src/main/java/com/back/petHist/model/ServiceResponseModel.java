package com.back.petHist.model;

import com.back.petHist.model.Paginado.PaginadoResponse;

import java.util.List;
import java.util.Objects;

public class ServiceResponseModel {
    Boolean success;
    Boolean records;
    String message;

    String token;

    List<?> dataListModel;
    Object dataModel;
    PaginadoResponse dataPaginado;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<?> getDataListModel() {
        return dataListModel;
    }

    public void setDataListModel(List<?> dataListModel) {
        this.dataListModel = dataListModel;
    }

    public Object getDataModel() {
        return dataModel;
    }

    public void setDataModel(Object dataModel) {
        this.dataModel = dataModel;
    }

    public Boolean getRecords() {
        return records;
    }

    public void setRecords(Boolean records) {
        this.records = records;
    }

    public PaginadoResponse getDataPaginado() {
        return dataPaginado;
    }

    public void setDataPaginado(PaginadoResponse dataPaginado,int totalRegistro, int numeroRegistros, int numeroPagina) {
        int totalRegistros = totalRegistro;
        int totalPaginaInt=0;
        int totalPaginaDec=0;
        totalPaginaInt = (totalRegistros / numeroRegistros);
        totalPaginaDec = (totalRegistros % numeroRegistros);
        if (totalPaginaDec > 0)
        {
            totalPaginaInt = totalPaginaInt + 1;
        }
        dataPaginado.setTotalCount(totalRegistros);
        dataPaginado.setPageSize(numeroRegistros);
        dataPaginado.setCurrentPage(numeroPagina);
        dataPaginado.setTotalPages(totalPaginaInt);

        this.dataPaginado = dataPaginado;
    }

}
