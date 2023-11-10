package com.mingles.metamingle.member.command.infrastructure.service;

import com.google.cloud.BaseServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    private MimeMessage createMessage(String code, String email) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, email);
        message.setSubject("Meta Mingle 회원가입 본인인증 코드입니다.");
        message.setText("인증코드:  " + code);
        message.setFrom("metamingle@naver.com");
        return message;
    }

    public String sendMail(String email) {

        String code = UUID.randomUUID().toString().substring(0, 8);

        try {
            MimeMessage message = createMessage(code, email);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return code;
    }

}