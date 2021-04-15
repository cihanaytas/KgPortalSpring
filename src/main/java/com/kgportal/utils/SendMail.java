package com.kgportal.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class SendMail {
	
    @Autowired
    private JavaMailSender mailSender;
    


    @Async
    public String sendMail(String mailAddress, String Text, String Subject) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

        try {
            messageHelper.setTo(mailAddress);
            messageHelper.setText(Text);
            messageHelper.setSubject(Subject);
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error...";
        }
        mailSender.send(mimeMessage);
        return "Mail Sent!";
    }
}
