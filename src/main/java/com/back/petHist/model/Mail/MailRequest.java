package com.back.petHist.model.Mail;

import lombok.Data;

@Data
public class MailRequest {
    String from;
    String to;
    String subject;
    String body;
}