package com.softserve.ukrainer.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class UkrainerConfig {

    @Value("${mail.host}")
    private String mailHost;
    @Value("${mail.protocol}")
    private String mailProtocol;
    @Value("${mail.username}")
    private String mailUsername;
    @Value("${mail.password}")
    private String mailPassword;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(587);
        mailSender.setProtocol(mailProtocol);
        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);

        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", true);
        mailProperties.put("mail.smtp.starttls.enable", true);
        mailSender.setJavaMailProperties(mailProperties);

        return mailSender;
    }
}
