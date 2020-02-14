package com.mail.service.impl;

import com.mail.domain.Mail;
import com.mail.dto.UserDTO;
import com.mail.repository.MailRepository;
import com.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailServiceImpl implements EmailService {

    public JavaMailSender emailSender;

    public MailRepository mailRepository;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender,
                            MailRepository mailRepository) {
        this.emailSender = emailSender;
        this.mailRepository = mailRepository;
    }

    @Override
    public void sendSimpleMessage(UserDTO input) {
        try {

            Mail newMail = new Mail();
            newMail.setTo(input.getUsername());
            newMail.setSubject("TestSubject");
            newMail.setText("TestText");

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(newMail.getTo());
            message.setSubject(newMail.getSubject());
            message.setText(newMail.getText());

            mailRepository.save(newMail);

            emailSender.send(message);

        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }
}
