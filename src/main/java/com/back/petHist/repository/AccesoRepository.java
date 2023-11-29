package com.back.petHist.repository;

import com.back.petHist.model.Acceso.*;
import com.back.petHist.model.MantenimientoResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.back.petHist.model.Authentication.AuthCredentials;
import com.back.petHist.model.Mail.MailRequest;
import com.back.petHist.model.ServiceResponseModel;
import com.back.petHist.model.Usuario.UsuarioFindRequest;
import com.back.petHist.model.Usuario.UsuarioListResponse;
import com.back.petHist.model.Usuario.UsuarioLoginResponse;
import com.back.petHist.security.TokenUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import java.util.List;
@Repository
public class AccesoRepository implements IAccesoRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final TokenUtils tokenUtils;
    private final RestTemplate restTemplate;

    public AccesoRepository(TokenUtils tokenUtils,RestTemplate restTemplate) {
        this.tokenUtils = tokenUtils;
        this.restTemplate=restTemplate;

    }
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
    @Override
    public String recuperarContrasenia(RecuperarContrasenia recuperarContrasenia) {
        String SQL = "EXEC sp_RecuperarContrasenia ?";
        Object[] params = {
                recuperarContrasenia.getCorreo()
        };
        Integer idUsuario = jdbcTemplate.queryForObject(SQL, params, Integer.class);

        UsuarioLoginResponse usuarioLoginResponse = new UsuarioLoginResponse();
        usuarioLoginResponse.setnIdUsuario(idUsuario);

        String token = tokenUtils.createToken(usuarioLoginResponse);
        String respuestaEnvioCorreo="";

        //Enviar correo
        // Configurar encabezados para la solicitud POST
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MailRequest requestBody=new MailRequest();
        requestBody.setTo(recuperarContrasenia.getCorreo());
        requestBody.setSubject("ASUNTO");
        requestBody.setBody("Url a ingresar: "+"http://localhost:4200/cambiar-clave/"+token);

        // Crear la entidad HTTP con el cuerpo de la solicitud y los encabezados
        HttpEntity<MailRequest> requestEntity = new HttpEntity<>(requestBody, headers);

        // Hacer una solicitud POST al servicio externo
        ServiceResponseModel respuesta = restTemplate.postForObject("http://localhost:9000/api/control/mail/enviarCorreo", requestEntity, ServiceResponseModel.class);
        if(respuesta.getSuccess()){
            respuestaEnvioCorreo="Exito";
        }else{
            respuestaEnvioCorreo="Error";
        }
        return respuestaEnvioCorreo;
    }

}
