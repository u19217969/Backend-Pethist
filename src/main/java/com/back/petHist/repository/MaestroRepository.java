package com.back.petHist.repository;

import com.back.petHist.model.Maestro.MaestroResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MaestroRepository implements IMaestroRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<MaestroResponseModel> listarMaestro(int parametro) {
        String SQL = "EXEC sp_MaestroSistemas ?";
        Object[] params = {
                parametro
        };
        List<MaestroResponseModel> resultados = jdbcTemplate.query(SQL, params, new BeanPropertyRowMapper<>(MaestroResponseModel.class));
        return resultados;

    }
}
