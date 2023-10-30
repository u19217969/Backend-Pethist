package com.back.petHist.controller;

import com.back.petHist.model.Cita.CitaListRequest;
import com.back.petHist.model.Cita.CitaRequest;
import com.back.petHist.model.Cita.HorarioFechaRequest;
import com.back.petHist.model.Constantes;
import com.back.petHist.model.Paginado.PaginadoResponse;
import com.back.petHist.model.ServiceResponseModel;
import com.back.petHist.service.ICitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/control/cita")
@CrossOrigin("*")
public class CitaController {
    @Autowired
    private ICitaService iCitaService;
    @PostMapping("/horarioFecha")
    public ResponseEntity<ServiceResponseModel> horarioFecha(@RequestBody HorarioFechaRequest horarioFechaRequest){
        ServiceResponseModel serviceResponse=new ServiceResponseModel();
        try {
            var result=iCitaService.horarioFecha(horarioFechaRequest);
            if (result==null){
                serviceResponse.setRecords(false);
                serviceResponse.setMessage(Constantes.messageListaTablaVacio);
            }else{
                serviceResponse.setRecords(true);
                serviceResponse.setMessage(Constantes.messageListaTabla);
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
    @PostMapping("/mantenimiento")
    public ResponseEntity<ServiceResponseModel> mantenimientoCita(@RequestBody CitaRequest citaRequest){
        ServiceResponseModel serviceResponse=new ServiceResponseModel();
        try {
            var result=iCitaService.mantenimientoCita(citaRequest);
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
    public ResponseEntity<ServiceResponseModel> citaListar(@RequestBody CitaListRequest citaListRequest){
        ServiceResponseModel serviceResponse=new ServiceResponseModel();
        try {
            var result=iCitaService.citaLista(citaListRequest);
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
                        citaListRequest.getNumeroRegistros(),
                        citaListRequest.getNumeroPagina());

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
