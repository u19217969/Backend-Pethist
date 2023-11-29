package com.back.petHist.service;

import com.back.petHist.model.Mail.MailRequest;
import com.back.petHist.model.MantenimientoResponseModel;

public interface IMailService {
    public MantenimientoResponseModel enviarCorreo(MailRequest mailRequest);
}