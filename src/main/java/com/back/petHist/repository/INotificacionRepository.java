package com.back.petHist.repository;

import com.back.petHist.model.Notificacion.NotificacionListRequest;
import com.back.petHist.model.Notificacion.NotificacionListResponse;

import java.util.List;

public interface INotificacionRepository {
    public List<NotificacionListResponse> notificacionLista(NotificacionListRequest notificacionListRequest);
}
