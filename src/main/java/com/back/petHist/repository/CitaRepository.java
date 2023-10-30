package com.back.petHist.repository;

import com.back.petHist.model.Cita.*;
import com.back.petHist.model.MantenimientoResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class CitaRepository implements ICitaRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<HorarioFechaListResponse> horarioFecha(HorarioFechaRequest horarioFechaRequest) {
        String SQLMENU = "EXEC sp_HorarioFecha ?, ?";
        Object[] paramsMenu = {
                horarioFechaRequest.getFechaInicio(),
                horarioFechaRequest.getIdUsuario()
        };

        List<HorarioFechaListResponse> horarioFechaListResponses = jdbcTemplate.query(SQLMENU, paramsMenu, new BeanPropertyRowMapper<>(HorarioFechaListResponse.class));

        if (horarioFechaListResponses.size()==0) {
            return null;
        } else {
            var contador=0;
            for (HorarioFechaListResponse horarioHoraListResponse : horarioFechaListResponses) {
                List<HorarioHoraListResponse> horarioHoraListResponses = horarioHora(
                        horarioFechaRequest.getIdUsuario(),
                        horarioHoraListResponse.getFecha());

                if (horarioHoraListResponses.size()!=0){
                    horarioFechaListResponses.get(contador).setHorario(horarioHoraListResponses);
                }
                contador++;

            }
        }
        return horarioFechaListResponses;
    }

    @Override
    public MantenimientoResponseModel mantenimientoCita(CitaRequest citaRequest) {
        String SQL = "EXEC sp_MantenimientoCita ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
        Object[] params = {
                citaRequest.getIdCita(),
                citaRequest.getIdDoctor(),
                citaRequest.getIdMascota(),
                citaRequest.getFechaCita(),
                citaRequest.getHoraCita(),
                citaRequest.getMotivo(),
                citaRequest.getObservacion(),
                citaRequest.getIdEstadoCita(),
                citaRequest.isEstado(),
                citaRequest.getCreaUsuario(),
                citaRequest.getModificaUsuario(),
                citaRequest.getFlag()
        };
        MantenimientoResponseModel resultados = jdbcTemplate.queryForObject(SQL, params, new BeanPropertyRowMapper<>(MantenimientoResponseModel.class));
        return resultados;
    }

    @Override
    public List<CitaListResponse> citaLista(CitaListRequest citaListRequest) {
        String SQL = "EXEC sp_ListarCitas ?, ?, ?, ?, ?, ?";
        Object[] params = {
                citaListRequest.getNumeroPagina(),
                citaListRequest.getNumeroRegistros(),
                citaListRequest.getFechaCita(),
                citaListRequest.getIdCliente(),
                citaListRequest.getIdMascota(),
                citaListRequest.getFiltro()
        };
        List<CitaListResponse>  resultados = jdbcTemplate.query(SQL, params, new BeanPropertyRowMapper<>(CitaListResponse.class));

        return resultados;
    }

    private List<HorarioHoraListResponse> horarioHora(int idUsuario, Date fechaInicio){
        String SQL = "EXEC sp_HorarioHora ?, ?";
        Object[] params = {
                idUsuario,
                fechaInicio
        };
        List<HorarioHoraListResponse> resultados = jdbcTemplate.query(SQL, params, new BeanPropertyRowMapper<>(HorarioHoraListResponse.class));
        return resultados;

    }
}
