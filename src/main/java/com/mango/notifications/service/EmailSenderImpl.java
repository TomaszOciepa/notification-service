package com.mango.notifications.service;

import com.mango.notifications.dto.EmailDto;
import com.mango.notifications.dto.NotificationInfoDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailSenderImpl implements EmailSender {

    private final JavaMailSender javaMailSender;

    public EmailSenderImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmails(NotificationInfoDto notificationInfo) {
        String title = "Pamiętaj o kursie: " + notificationInfo.getCourseName();
        StringBuilder content = createEmailContent(notificationInfo);

        notificationInfo.getEmails().forEach(email -> {
            try {
                sendEmail(email, title, content.toString());
            } catch (MessagingException e) {
                log.error("Notyfikacja się nie wysłała!!!!", e);
            }
        });
    }

    private StringBuilder createEmailContent(NotificationInfoDto notificationInfo) {
        StringBuilder content = new StringBuilder();
        content.append("Kurs ");
        content.append(notificationInfo.getCourseName());
        content.append(" rozpoczyna się");
        content.append(notificationInfo.getCourseStartDate().toLocalDate());
        content.append("o godzinie: ");
        content.append(notificationInfo.getCourseStartDate().getHour() + ":" + notificationInfo.getCourseStartDate().getMinute());
        content.append(". Proszę stawić się 15min wcześniej!");
        content.append("\n");
        content.append("Opis kursu: ");
        content.append(notificationInfo.getCourseDescription());
        content.append("\n");
        content.append("Kurs kończy się ");
        content.append(notificationInfo.getCourseEndDate().getHour() + ":" + notificationInfo.getCourseEndDate().getMinute());
        content.append("\n");
        content.append("Czekamy na Ciebie");
        return content;
    }

    @Override
    public void sendEmail(EmailDto emailDto) throws MessagingException {
        sendEmail(emailDto.getTo(), emailDto.getTitle(), emailDto.getContent());
    }

    private void sendEmail(String to, String title, String content) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(title);
        mimeMessageHelper.setText(content, false);
        javaMailSender.send(mimeMessage);
    }
}
