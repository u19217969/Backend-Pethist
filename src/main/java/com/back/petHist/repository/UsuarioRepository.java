package com.back.petHist.repository;

import com.back.petHist.model.MantenimientoResponseModel;
import com.back.petHist.model.Usuario.*;
import com.back.petHist.model.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class UsuarioRepository implements IUsuarioRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public UsuarioListResponse buscarUsuario(UsuarioFindRequest usuarioFindRequest) {
        String SQL = "EXEC sp_ObtenerUsuario ?";
        Object[] params = {
                usuarioFindRequest.getIdUsuario()
        };
        UsuarioListResponse  resultados = jdbcTemplate.queryForObject(SQL, params, new BeanPropertyRowMapper<>(UsuarioListResponse.class));

        return resultados;
    }

    @Override
    public MantenimientoResponseModel mantenimientoUsuario(UsuarioRequest usuarioRequest) {
        String SQL = "EXEC sp_MantenimientoUsuario ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
        String claveEncriptada="";
        if (usuarioRequest.getClave()!=""){
            claveEncriptada=new BCryptPasswordEncoder().encode(usuarioRequest.getClave());
        }
        Object[] params = {
                usuarioRequest.getIdUsuario(),
                usuarioRequest.getIdTipoDocumento(),
                usuarioRequest.getIdTipoUsuario(),
                usuarioRequest.getNombreUsuario(),
                usuarioRequest.getLogin(),
                usuarioRequest.getNroDocumento(),
                claveEncriptada,
                usuarioRequest.getCorreo(),
                usuarioRequest.isEstado(),
                usuarioRequest.getCreaUsuario(),
                usuarioRequest.getModificaUsuario(),
                usuarioRequest.getFlag()
        };
        MantenimientoResponseModel resultados = jdbcTemplate.queryForObject(SQL, params, new BeanPropertyRowMapper<>(MantenimientoResponseModel.class));
        return resultados;
    }
    @Override
    public MantenimientoResponseModel actualizarContrasenia(ActualizarContraseniaRequest actualizarContraseniaRequest) {
        String SQL = "EXEC sp_ActualizarContrasenia ?, ?";
        String claveEncriptada="";
        if (actualizarContraseniaRequest.getContrasenia()!=""){
            claveEncriptada=new BCryptPasswordEncoder().encode(actualizarContraseniaRequest.getContrasenia());
        }
        Object[] params = {
                actualizarContraseniaRequest.getIdUsuario(),
                claveEncriptada
        };
        MantenimientoResponseModel resultados = jdbcTemplate.queryForObject(SQL, params, new BeanPropertyRowMapper<>(MantenimientoResponseModel.class));
        return resultados;
    }
    @Override
    public List<UsuarioListResponse> usuarioLista(UsuarioListRequest usuarioListRequest) {
        String SQL = "EXEC sp_ListarUsuarios ?, ?, ?, ?, ?, ?, ?";
        Object[] params = {
                usuarioListRequest.getNumeroPagina(),
                usuarioListRequest.getNumeroRegistros(),
                usuarioListRequest.getIdTipoDocumento(),
                usuarioListRequest.getNroDocumento(),
                usuarioListRequest.getIdTipoUsuario(),
                usuarioListRequest.getFiltro(),
                usuarioListRequest.getIdUsuarioLogueado()
        };
        List<UsuarioListResponse>  resultados = jdbcTemplate.query(SQL, params, new BeanPropertyRowMapper<>(UsuarioListResponse.class));

        return resultados;
    }

    @Override
    public Optional<UsuarioRecuperarResponse> usuarioLoginResponse(String cCorreo) {
        String SQL = "EXEC sp_LoginUsuario ?";
        UsuarioRecuperarResponse usuario= jdbcTemplate.queryForObject(SQL, BeanPropertyRowMapper.newInstance(UsuarioRecuperarResponse.class),
                cCorreo);
        if (usuario != null) {
            return Optional.of(usuario);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public int save(UsuarioModel usuario) {
        return 0;
    }

    @Override
    public int update(UsuarioModel usuario) {
        return 0;
    }

    @Override
    public int deleteById(int idUsuario) {
        return 0;
    }

}
