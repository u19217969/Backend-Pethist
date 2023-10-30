package com.back.petHist.repository;

import com.back.petHist.model.MantenimientoResponseModel;
import com.back.petHist.model.Mascota.MascotaListRequest;
import com.back.petHist.model.Mascota.MascotaListResponse;
import com.back.petHist.model.Mascota.MascotaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MascotaRepository implements IMascotaRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public MantenimientoResponseModel mantenimientoMascota(MascotaRequest mascotaRequest) {
        String SQL = "EXEC sp_MantenimientoMascota ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
        Object[] params = {
                mascotaRequest.getIdMascota(),
                mascotaRequest.getIdGeneroMascota(),
                mascotaRequest.getIdTipoMascota(),
                mascotaRequest.getNombreMascota(),
                mascotaRequest.getFechaNacimiento(),
                mascotaRequest.getColorMascota(),
                mascotaRequest.getIdUsuario(),
                mascotaRequest.isEstado(),
                mascotaRequest.getCreaUsuario(),
                mascotaRequest.getModificaUsuario(),
                mascotaRequest.getFlag()
        };
        MantenimientoResponseModel resultados = jdbcTemplate.queryForObject(SQL, params, new BeanPropertyRowMapper<>(MantenimientoResponseModel.class));
        return resultados;
    }

    @Override
    public List<MascotaListResponse> mascotaLista(MascotaListRequest mascotaListRequest) {
        String SQL = "EXEC sp_ListarMascotas ?, ?, ?, ?, ?, ?, ?";
        Object[] params = {
                mascotaListRequest.getNumeroPagina(),
                mascotaListRequest.getNumeroRegistros(),
                mascotaListRequest.getIdGenero(),
                mascotaListRequest.getIdTipo(),
                mascotaListRequest.getFechaNacimiento(),
                mascotaListRequest.getIdCliente(),
                mascotaListRequest.getFiltro()
        };
        List<MascotaListResponse>  resultados = jdbcTemplate.query(SQL, params, new BeanPropertyRowMapper<>(MascotaListResponse.class));

        return resultados;
    }
}
