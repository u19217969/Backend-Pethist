package com.back.petHist.service;

import com.back.petHist.model.Notificacion.NotificacionListRequest;
import com.back.petHist.model.Notificacion.NotificacionListResponse;
import com.back.petHist.repository.INotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotificacionService implements INotificacionService{
    @Autowired
    private INotificacionRepository iNotificacionRepository;
    @Override
    public List<NotificacionListResponse> notificacionLista(NotificacionListRequest notificacionListRequest) {
        List<NotificacionListResponse>  notificacionListResponse;
        try {
            notificacionListResponse=iNotificacionRepository.notificacionLista(notificacionListRequest);
        }catch (Exception ex){
            throw ex;
        }
        return notificacionListResponse;
    }

    @Override
    public List<String> idsAdministration() {
        List<String> ids;
        try {
            ids=iNotificacionRepository.idsAdministration();
        }catch(Exception e){
            throw e;
        }
        return ids;
    }
}
