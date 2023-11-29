package com.back.petHist.controller;

import com.back.petHist.model.Constantes;
import com.back.petHist.model.Notificacion.NotificacionListRequest;
import com.back.petHist.model.Paginado.PaginadoResponse;
import com.back.petHist.model.ServiceResponseModel;
import com.back.petHist.service.INotificacionService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("notificationSSE")
public class NotificationControllerSSE {
    @Autowired
    private INotificacionService iNotificacionService;


    public Map<String, SseEmitter> emitters = new HashMap<>();

    //method for client subscription(metodo para la suscripcion del cliente)
    @RequestMapping(value = "/subscribe", consumes = MediaType.ALL_VALUE)
    public SseEmitter subscribe(@RequestParam String userID) {
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        sendInitEvent(sseEmitter);
        emitters.put(userID, sseEmitter);
        //emitters.add(sseEmitter);
        sseEmitter.onCompletion(() -> emitters.remove(sseEmitter));
        sseEmitter.onTimeout(() -> emitters.remove(sseEmitter));
        sseEmitter.onError((error) -> emitters.remove(sseEmitter));
        return sseEmitter;
    }

    //method for dispatching events to a Specific User
    @PostMapping(value = "/dispatchEventToSpecificUser")
    public void dispatchEventToClients(@RequestBody NotificacionListRequest notificacionListRequest) {
        //USUARIO
        System.out.println(notificacionListRequest.getIdUsuario());
        notificationEvent(
                null,
                String.valueOf(notificacionListRequest.getIdUsuario()),
                resultFormatterJSON(notificacionListRequest));
        ArrayList<String> ids = (ArrayList<String>) iNotificacionService.idsAdministration();
        //ADMINISTRADOR
        notificacionListRequest.setIdUsuario(0);
        notificationEvent(
                ids,
                null,
                resultFormatterJSON(notificacionListRequest));
    }

    public String resultFormatterJSON(NotificacionListRequest notificacionListRequest) {
        return String.valueOf(new JSONObject()
                .put("result", iNotificacionService.notificacionLista(notificacionListRequest)
        ));
    }

    public void notificationEvent(ArrayList<String> ids, String idUser, String eventFormatted) {
        if (ids!=null) {
            ids.forEach((id) -> {
                SseEmitter sseEmitter = emitters.get(id);
                if (sseEmitter != null) {
                    try {
                        sseEmitter.send(SseEmitter.event().name("latestNews").data(eventFormatted));
                    } catch (Exception e) {
                        emitters.remove(sseEmitter);
                    }
                }
            });
        } else {
            SseEmitter sseEmitter = emitters.get(idUser);
            if (sseEmitter != null) {
                try {
                    sseEmitter.send(SseEmitter.event().name("latestNews").data(eventFormatted));
                } catch (Exception e) {
                    emitters.remove(sseEmitter);
                }
            }
        }
    }

    private void sendInitEvent(SseEmitter sseEmitter) {
        try {
            sseEmitter.send(SseEmitter.event().name("INIT"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
