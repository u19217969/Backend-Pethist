package com.back.petHist.controller;


import com.back.petHist.model.Constantes;
import com.back.petHist.model.Maestro.TablaRequestModel;
import com.back.petHist.model.ServiceResponseModel;
import com.back.petHist.service.IMaestroService;
import com.back.petHist.service.ITablaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/control/maestros")
@CrossOrigin("*")
public class MaestroController {
    @Autowired
    private IMaestroService iMaestroService;
    @Autowired
    private ITablaService iTablaService;
    @GetMapping("/{parametro}")
    public ResponseEntity<ServiceResponseModel> listarMaestro(@PathVariable int parametro){
        ServiceResponseModel serviceResponse=new ServiceResponseModel();
        try {
            var result=iMaestroService.listarMaestro(parametro);
            if (result.size()==0){
                serviceResponse.setRecords(false);
                serviceResponse.setMessage(Constantes.messageListaMaestroVacio);
            }else{
                serviceResponse.setRecords(true);
                serviceResponse.setMessage(Constantes.messageListaMaestroVacio);
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
    @PostMapping("/tabla")
    public ResponseEntity<ServiceResponseModel> listarTabla(@RequestBody TablaRequestModel tablaRequestModel){
        ServiceResponseModel serviceResponse=new ServiceResponseModel();
        try {
            var result=iTablaService.listarTabla(tablaRequestModel);
            if (result.size()==0){
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
            serviceResponse.setMessage(e.getMessage());
            return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
        }
    }
}
