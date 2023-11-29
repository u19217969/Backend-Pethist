package com.back.petHist.controller;

import com.back.petHist.model.Constantes;
import com.back.petHist.model.Mail.MailRequest;
import com.back.petHist.model.ServiceResponseModel;
import com.back.petHist.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/control/mail")
@CrossOrigin("*")
public class MailController {
    @Autowired
    private IMailService iMailService;
    @PostMapping("/enviarCorreo")
    public ResponseEntity<ServiceResponseModel> enviarCorreo(@RequestBody MailRequest mailRequest){
        ServiceResponseModel serviceResponse=new ServiceResponseModel();
        try {
            var result=iMailService.enviarCorreo(mailRequest);
            if (result==null){
                serviceResponse.setRecords(false);
                serviceResponse.setMessage(Constantes.messageListaTablaVacio);
            }else{
                serviceResponse.setRecords(true);
                serviceResponse.setMessage("");
            }
            serviceResponse.setSuccess(true);
            return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
        }catch (Exception e){
            serviceResponse.setSuccess(false);
            serviceResponse.setMessage(Constantes.messageErrorServidor);
            return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
        }
    }
}