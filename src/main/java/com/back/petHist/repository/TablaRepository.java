package com.back.petHist.repository;

import com.back.petHist.model.Maestro.MaestroResponseModel;
import com.back.petHist.model.Maestro.TablaRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class TablaRepository implements ITablaRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<MaestroResponseModel> listarTabla(TablaRequestModel tablaRequestModel) {
        String SQL = "EXEC sp_ListarTablas ?, ?";
        Object[] params = {
                tablaRequestModel.getTabla(),
                tablaRequestModel.getId()
        };
        List<MaestroResponseModel> resultados = jdbcTemplate.query(SQL, params, new BeanPropertyRowMapper<>(MaestroResponseModel.class));
        return resultados;
    }
}
