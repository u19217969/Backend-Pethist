package com.back.petHist.controller;

import com.back.petHist.model.Constantes;
import com.back.petHist.model.Paginado.PaginadoResponse;
import com.back.petHist.model.ServiceResponseModel;
import com.back.petHist.model.Usuario.UsuarioFindRequest;
import com.back.petHist.model.Usuario.UsuarioListRequest;
import com.back.petHist.model.Usuario.UsuarioRequest;
import com.back.petHist.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.back.petHist.model.Usuario.ActualizarContraseniaRequest;
@RestController
@RequestMapping("api/control/usuario")
@CrossOrigin("*")   
public class UsuarioController {
    @Autowired
    private IUsuarioService iUsuarioService;

    @PostMapping("/buscar")
    public ResponseEntity<ServiceResponseModel> buscarUsuario(@RequestBody UsuarioFindRequest usuarioFindRequest){
        ServiceResponseModel serviceResponse=new ServiceResponseModel();
        try {
            var result=iUsuarioService.buscarUsuario(usuarioFindRequest);
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
    @PostMapping("/mantenimiento")
    public ResponseEntity<ServiceResponseModel> mantenimientoUsuario(@RequestBody UsuarioRequest usuarioRequest){
        ServiceResponseModel serviceResponse=new ServiceResponseModel();
        try {
            var result=iUsuarioService.mantenimientoUsuario(usuarioRequest);
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
    public ResponseEntity<ServiceResponseModel> usuarioLista(@RequestBody UsuarioListRequest usuarioListRequest){
        ServiceResponseModel serviceResponse=new ServiceResponseModel();
        try {
            var result=iUsuarioService.usuarioLista(usuarioListRequest);
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
                        usuarioListRequest.getNumeroRegistros(),
                        usuarioListRequest.getNumeroPagina());

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

    @PostMapping("/actualizarContrasenia")
    public ResponseEntity<ServiceResponseModel> actualizarContrasenia(@RequestBody ActualizarContraseniaRequest actualizarContraseniaRequest) {
        ServiceResponseModel serviceResponse = new ServiceResponseModel();
        try {
            var result = iUsuarioService.actualizarContrasenia(actualizarContraseniaRequest);
            if (result == null) {
                serviceResponse.setRecords(false);
                serviceResponse.setMessage(Constantes.messageListaTablaVacio);
            } else {
                serviceResponse.setRecords(true);
                serviceResponse.setMessage(Constantes.messageActualizarPass);
            }
            serviceResponse.setSuccess(true);
            serviceResponse.setDataModel(result);
            return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
        } catch (Exception e) {
            serviceResponse.setSuccess(false);
            serviceResponse.setMessage(Constantes.messageErrorServidor);
            return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
        }
    }
}
