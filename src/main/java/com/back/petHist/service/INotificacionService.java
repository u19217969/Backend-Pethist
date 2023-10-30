package com.back.petHist.service;

import com.back.petHist.model.Notificacion.NotificacionListRequest;
import com.back.petHist.model.Notificacion.NotificacionListResponse;

import java.util.List;

public interface INotificacionService {
    public List<NotificacionListResponse> notificacionLista(NotificacionListRequest notificacionListRequest);
}
