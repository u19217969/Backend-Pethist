package com.back.petHist.repository;

import com.back.petHist.model.Notificacion.NotificacionListRequest;
import com.back.petHist.model.Notificacion.NotificacionListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificacionRepository implements INotificacionRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<NotificacionListResponse> notificacionLista(NotificacionListRequest notificacionListRequest) {
        String SQL = "EXEC sp_ListarNotificaciones ?, ?, ?, ?, ?";
        Object[] params = {
                notificacionListRequest.getNumeroPagina(),
                notificacionListRequest.getNumeroRegistros(),
                notificacionListRequest.getIdUsuario(),
                notificacionListRequest.getFechaInicio(),
                notificacionListRequest.getFiltro()
        };
        List<NotificacionListResponse>  resultados = jdbcTemplate.query(SQL, params, new BeanPropertyRowMapper<>(NotificacionListResponse.class));

        return resultados;
    }
}
