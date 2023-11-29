package com.back.petHist.repository;

import com.back.petHist.model.Mail.MailRequest;
import com.back.petHist.model.MantenimientoResponseModel;

public interface IMailRepository {
    public MantenimientoResponseModel enviarCorreo(MailRequest mailRequest);
}