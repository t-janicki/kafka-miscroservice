package com.mail.service;

import com.mail.dto.UserDTO;

public interface EmailService {
    void sendSimpleMessage(UserDTO input);
}
