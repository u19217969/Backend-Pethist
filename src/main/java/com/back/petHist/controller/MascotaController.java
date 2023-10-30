package com.back.petHist.controller;

import com.back.petHist.model.Constantes;
import com.back.petHist.model.Mascota.MascotaListRequest;
import com.back.petHist.model.Mascota.MascotaRequest;
import com.back.petHist.model.Paginado.PaginadoResponse;
import com.back.petHist.model.ServiceResponseModel;
import com.back.petHist.service.IMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/control/mascota")
@CrossOrigin("*")
public class MascotaController {
    @Autowired
    private IMascotaService iMascotaService;

    @PostMapping("/mantenimiento")
    public ResponseEntity<ServiceResponseModel> mantenimientoMascota(@RequestBody MascotaRequest mascotaRequest){
        ServiceResponseModel serviceResponse=new ServiceResponseModel();
        try {
            var result=iMascotaService.mantenimientoMascota(mascotaRequest);
            if (result==null){
                serviceResponse.setRecords(false);
                serviceResponse.setMessage(Constantes.messageListaTablaVacio);
            }else{
                serviceResponse.setRecords(true);
                serviceResponse.setMessage(Constantes.messageListaTabla);
            }
            serviceResponse.setSuccess(true);
            serviceResponse.setDataModel(result);
            return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
        }catch (Exception e){
            serviceResponse.setSuccess(false);
            serviceResponse.setMessage(Constantes.messageErrorServidor);
            return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
        }
    }
    @PostMapping("/lista")
    public ResponseEntity<ServiceResponseModel> mascotaListar(@RequestBody MascotaListRequest mascotaListRequest){
        ServiceResponseModel serviceResponse=new ServiceResponseModel();
        try {
            var result=iMascotaService.mascotaLista(mascotaListRequest);
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
                        mascotaListRequest.getNumeroRegistros(),
                        mascotaListRequest.getNumeroPagina());

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
