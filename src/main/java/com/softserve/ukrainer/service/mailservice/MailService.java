package com.softserve.ukrainer.service.mailservice;

import com.softserve.ukrainer.dto.UserDTO;

public interface MailService {

    void sendEmail(UserDTO userDTO, String subject, String content);
}
