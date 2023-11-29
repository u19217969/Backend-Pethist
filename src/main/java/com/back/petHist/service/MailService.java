package com.back.petHist.service;

import com.back.petHist.model.Mail.MailRequest;
import com.back.petHist.model.MantenimientoResponseModel;
import com.back.petHist.repository.IMailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailService implements IMailService{
    @Autowired
    private IMailRepository iMailRepository;
    @Override
    public MantenimientoResponseModel enviarCorreo(MailRequest mailRequest) {
        MantenimientoResponseModel mantenimientoResponseModel;
        try {
            mantenimientoResponseModel=iMailRepository.enviarCorreo(mailRequest);
        }catch (Exception ex){
            throw ex;
        }
        return mantenimientoResponseModel;
    }
}