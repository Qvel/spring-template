package com.softserve.ukrainer.service.mailservice;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.softserve.ukrainer.constant.UkrainerConstant;
import com.softserve.ukrainer.dto.UserDTO;
import com.softserve.ukrainer.exception.UkrainerException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService{

    @Value("${mail.username}")
    private String mailUsername;

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(UserDTO userDTO, String subject, String content) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            mimeMessage.setContent(content, "text/html");
            helper.setTo(userDTO.getEmail());
            helper.setSubject(subject);
            helper.setFrom(mailUsername);
        } catch (MessagingException e) {
            log.error(UkrainerConstant.EMAIL_SENDING_EXCEPTION.getMessage(), e);
            throw new UkrainerException(UkrainerConstant.EMAIL_SENDING_EXCEPTION.getMessage(), 500);
        }
        javaMailSender.send(mimeMessage);
    }
}
