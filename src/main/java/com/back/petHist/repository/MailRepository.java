package com.back.petHist.repository;

import com.back.petHist.model.Mail.MailRequest;
import com.back.petHist.model.MantenimientoResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

@Repository
public class MailRepository implements IMailRepository{
    @Autowired
    private JavaMailSender emailSenderService;
    @Override
    public MantenimientoResponseModel enviarCorreo(MailRequest mailRequest) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("notificacionesjobifast@gmail.com");
        message.setTo(mailRequest.getTo());
        message.setSubject(mailRequest.getSubject());
        message.setText(mailRequest.getBody());
        emailSenderService.send(message);
        return null;
    }
}