package com.back.petHist.controller;

import com.back.petHist.model.Cita.CitaListRequest;
import com.back.petHist.model.Cita.CitaRequest;
import com.back.petHist.model.Cita.HorarioFechaRequest;
import com.back.petHist.model.Constantes;
import com.back.petHist.model.Notificacion.NotificacionListRequest;
import com.back.petHist.model.Paginado.PaginadoResponse;
import com.back.petHist.model.ServiceResponseModel;
import com.back.petHist.model.Usuario.UsuarioLoginResponse;
import com.back.petHist.service.ICitaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/control/cita")
@CrossOrigin("*")
public class CitaController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ICitaService iCitaService;

    @PostMapping("/horarioFecha")
    public ResponseEntity<ServiceResponseModel> horarioFecha(@RequestBody HorarioFechaRequest horarioFechaRequest) {
        ServiceResponseModel serviceResponse = new ServiceResponseModel();
        try {
            var result = iCitaService.horarioFecha(horarioFechaRequest);
            if (result == null) {
                serviceResponse.setRecords(false);
                serviceResponse.setMessage(Constantes.messageListaTablaVacio);
            } else {
                serviceResponse.setRecords(true);
                serviceResponse.setMessage(Constantes.messageListaTabla);
            }
            serviceResponse.setSuccess(true);
            serviceResponse.setDataListModel(result);
            return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
        } catch (Exception e) {
            serviceResponse.setSuccess(false);
            serviceResponse.setMessage(Constantes.messageErrorServidor);
            return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
        }
    }

    @PostMapping("/mantenimiento")
    public ResponseEntity<ServiceResponseModel> mantenimientoCita(@RequestBody CitaRequest citaRequest) {
        ServiceResponseModel serviceResponse = new ServiceResponseModel();
        UsuarioLoginResponse usuarioLoginResponse = new UsuarioLoginResponse();
        usuarioLoginResponse.setnIdUsuario(citaRequest.getIdUser());
    try {
            var result = iCitaService.mantenimientoCita(citaRequest);
            if (result == null) {
                serviceResponse.setRecords(false);
                serviceResponse.setMessage(Constantes.messageListaTablaVacio);
            } else {
                serviceResponse.setRecords(true);
                serviceResponse.setMessage(Constantes.messageListaTabla);
                sendNotification(citaRequest.getIdUser());
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

    public void sendNotification(int idUser) {
        NotificacionListRequest notificacionListRequest =new NotificacionListRequest();
        notificacionListRequest.setIdUsuario(idUser);
        notificacionListRequest.setNumeroPagina(1);
        notificacionListRequest.setNumeroRegistros(5);
        notificacionListRequest.setFiltro("");
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(notificacionListRequest);
            HttpEntity<String> entity = new HttpEntity<>(json, headers);
             restTemplate.exchange("http://localhost:9000/notificationSSE/dispatchEventToSpecificUser", HttpMethod.POST, entity,void.class).getBody();
            System.out.println("se ha realizado correctamente");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    @PostMapping("/lista")
    public ResponseEntity<ServiceResponseModel> citaListar(@RequestBody CitaListRequest citaListRequest) {
        ServiceResponseModel serviceResponse = new ServiceResponseModel();
        try {
            var result = iCitaService.citaLista(citaListRequest);
            if (result == null || result.size() == 0) {
                serviceResponse.setRecords(false);
                serviceResponse.setMessage(Constantes.messageListaTablaVacio);
            } else {
                serviceResponse.setRecords(true);
                serviceResponse.setMessage(Constantes.messageListaTabla);
                PaginadoResponse paginadoResponse = new PaginadoResponse();
                serviceResponse.setDataPaginado(
                        paginadoResponse,
                        result.get(0).getTotalRegistros(),
                        citaListRequest.getNumeroRegistros(),
                        citaListRequest.getNumeroPagina());

            }
            serviceResponse.setSuccess(true);
            serviceResponse.setDataListModel(result);
            return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
        } catch (Exception e) {
            serviceResponse.setSuccess(false);
            serviceResponse.setMessage(Constantes.messageErrorServidor);
            return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
        }
    }
}
