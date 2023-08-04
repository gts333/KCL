package com.kcl.service.impl;

import com.kcl.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class NotificationServiceImpl implements NotificationService {
    private JavaMailSender javaMailSender;

    @Autowired
    @SuppressWarnings("all")
    public NotificationServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * The current implementation sends an email to the administrator, uncomment and replace relevant fields in the method and application.yml
     * to send custom messages to custom email address
     * @param message the message to warn the administrator
     */
    @Override
    public void sendMessage(String message) {
        try {
            System.out.println(message);
//            SimpleMailMessage msg = new SimpleMailMessage();
//            msg.setFrom("email address from");
//            msg.setBcc();
//            msg.setTo("email addresss to");
//            msg.setSubject("Failure to handle requests automatically");
//            msg.setText(message);
//            javaMailSender.send(msg);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
