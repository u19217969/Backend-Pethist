package com.back.petHist.repository;

import com.back.petHist.model.Acceso.*;
import com.back.petHist.model.MantenimientoResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class AccesoRepository implements IAccesoRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<UsuarioAccesoResponse> accesoUsuario(UsuarioAccesoRequest usuarioAccesoRequest) {
        String SQLMENU = "EXEC sp_UsuarioAccesos ?";
        Object[] paramsMenu = {
                usuarioAccesoRequest.getnIdUsuario()};

        List<UsuarioAccesoResponse> listUsuarioAccesoResponse = jdbcTemplate.query(SQLMENU, paramsMenu, new BeanPropertyRowMapper<>(UsuarioAccesoResponse.class));

        if (listUsuarioAccesoResponse.size()==0) {
            return null;
        } else {
            var contador=0;
            for (UsuarioAccesoResponse usuarioAccesoResponse : listUsuarioAccesoResponse) {
                List<UsuarioAccesoHijoResponse> listUsuarioAccesoHijoResponse = accesoHijo(
                        usuarioAccesoRequest.getnIdUsuario(),
                        usuarioAccesoResponse.getnIdMenu());

                if (listUsuarioAccesoHijoResponse.size()!=0){
                    listUsuarioAccesoResponse.get(contador).setUsuarioAccesoHijo(listUsuarioAccesoHijoResponse);
                }
                contador++;

            }
        }
        return listUsuarioAccesoResponse;
    }
    private List<UsuarioAccesoHijoResponse> accesoHijo(int idUsuario,int idMenu){
        String SQL = "EXEC sp_UsuarioAccesosHijo ?, ?";
        Object[] params = {
                idUsuario,
                idMenu
        };
        List<UsuarioAccesoHijoResponse> resultados = jdbcTemplate.query(SQL, params, new BeanPropertyRowMapper<>(UsuarioAccesoHijoResponse.class));
        return resultados;

    }

    @Override
    public MantenimientoResponseModel mantenimientoAcceso(AccesoRequest[] accesoRequest) {
        String SQL = "EXEC sp_RegistrarAcceso ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
        MantenimientoResponseModel resultados=new MantenimientoResponseModel();
        for (AccesoRequest accessRequest : accesoRequest) {
            Object[] params = {
                    accessRequest.getIdUsuario(),
                    accessRequest.getIdMenu(),
                    accessRequest.isVer(),
                    accessRequest.isRegistrar(),
                    accessRequest.isActualizar(),
                    accessRequest.isEliminar(),
                    accessRequest.isAcceso(),
                    accessRequest.isProcesar(),
                    accessRequest.getCreaUsuario(),
                    accessRequest.getModificaUsuario()
            };
            resultados = jdbcTemplate.queryForObject(SQL, params, new BeanPropertyRowMapper<>(MantenimientoResponseModel.class));
            if (resultados.getTipo().equals("success")){
                if (accessRequest.getUsuarioAccesoHijo()!=null){
                    for (AccesoHijoRequest accessHijoRequest : accessRequest.getUsuarioAccesoHijo()) {
                        accessHijoRequest.setIdUsuario(accessRequest.getIdUsuario());
                        accessHijoRequest.setCreaUsuario(accessRequest.getCreaUsuario());
                        accessHijoRequest.setModificaUsuario(accessRequest.getModificaUsuario());
                        MantenimientoResponseModel resultadosHijos = mantenimientoAccesoHijo(
                                accessHijoRequest);
                    }
                }


            }
        }
        return resultados;
    }

    private MantenimientoResponseModel mantenimientoAccesoHijo(AccesoHijoRequest accesoRequest){
        String SQL = "EXEC sp_RegistrarAcceso ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
        Object[] params = {
                accesoRequest.getIdUsuario(),
                accesoRequest.getIdMenuHijo(),
                accesoRequest.isVer(),
                accesoRequest.isRegistrar(),
                accesoRequest.isActualizar(),
                accesoRequest.isEliminar(),
                accesoRequest.isAcceso(),
                accesoRequest.isProcesar(),
                accesoRequest.getCreaUsuario(),
                accesoRequest.getModificaUsuario()
        };
        MantenimientoResponseModel resultados = jdbcTemplate.queryForObject(SQL, params, new BeanPropertyRowMapper<>(MantenimientoResponseModel.class));
        return resultados;

    }


}
