package com.mango.notifications.service;


import com.mango.notifications.dto.EmailDto;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailSenderTest {

    @Autowired
    EmailSender emailSender;

    @Test
    public void send_email_test() throws MessagingException {
        EmailDto emailDto = EmailDto.builder()
                .to("tom.ociepa@gmail.com")
                .title( "hejo!!")
                .content("test wysyłnia emails")
                .build();

        emailSender.sendEmail(emailDto);
    }
}
