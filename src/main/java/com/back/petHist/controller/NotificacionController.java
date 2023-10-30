package com.back.petHist.controller;

import com.back.petHist.model.Constantes;
import com.back.petHist.model.Notificacion.NotificacionListRequest;
import com.back.petHist.model.Paginado.PaginadoResponse;
import com.back.petHist.model.ServiceResponseModel;
import com.back.petHist.service.INotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/control/notificacion")
@CrossOrigin("*")
public class NotificacionController {
    @Autowired
    private INotificacionService iNotificacionService;

    @PostMapping("/lista")
    public ResponseEntity<ServiceResponseModel> notificacionListar(@RequestBody NotificacionListRequest notificacionListRequest){
        ServiceResponseModel serviceResponse=new ServiceResponseModel();
        try {
            var result=iNotificacionService.notificacionLista(notificacionListRequest);
            if (result==null || result.size()==0){
                serviceResponse.setRecords(false);
                serviceResponse.setMessage(Constantes.messageListaTablaVacio);
            }else{
                serviceResponse.setRecords(true);
                serviceResponse.setMessage(Constantes.messageListaTabla);
                PaginadoResponse paginadoResponse=new PaginadoResponse();
                serviceResponse.setDataPaginado(
                        paginadoResponse,
                        result.get(0).getTotalRegistros(),
                        notificacionListRequest.getNumeroRegistros(),
                        notificacionListRequest.getNumeroPagina());

            }
            serviceResponse.setSuccess(true);
            serviceResponse.setDataListModel(result);
            return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
        }catch (Exception e){
            serviceResponse.setSuccess(false);
            serviceResponse.setMessage(Constantes.messageErrorServidor);
            return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
        }
    }
}
