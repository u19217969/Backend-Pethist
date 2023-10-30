package com.back.petHist.controller;

import com.back.petHist.model.Acceso.AccesoRequest;
import com.back.petHist.model.Acceso.UsuarioAccesoRequest;
import com.back.petHist.model.Constantes;
import com.back.petHist.model.ServiceResponseModel;
import com.back.petHist.service.IAccesoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/control/accesos")
@CrossOrigin("*")
public class AccesoController {
    @Autowired
    private IAccesoService iAccesoService;
    @PostMapping("/accesoUsuario")
    public ResponseEntity<ServiceResponseModel> accesoUsuario(@RequestBody UsuarioAccesoRequest usuarioAccesoRequest){
        ServiceResponseModel serviceResponse=new ServiceResponseModel();
        try {
            var result=iAccesoService.accesoUsuario(usuarioAccesoRequest);
            if (result==null){
                serviceResponse.setRecords(false);
                serviceResponse.setMessage(Constantes.messageListaUsuariosAccesoVacio);
            }else{
                serviceResponse.setRecords(true);
                serviceResponse.setMessage(Constantes.messageListaUsuariosAcceso);
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
    public ResponseEntity<ServiceResponseModel> mantenimientoAcceso(@RequestBody AccesoRequest[] accesoRequest){
        ServiceResponseModel serviceResponse=new ServiceResponseModel();
        try {
            var result=iAccesoService.mantenimientoAcceso(accesoRequest);
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
}
