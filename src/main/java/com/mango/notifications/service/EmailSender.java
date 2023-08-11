package com.mango.notifications.service;

import com.mango.notifications.dto.EmailDto;
import com.mango.notifications.dto.NotificationInfoDto;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

public interface EmailSender {
    void sendEmails(NotificationInfoDto notificationInfo);
     void sendEmail(EmailDto emailDto) throws MessagingException;
}
