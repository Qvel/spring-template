package com.softserve.ukrainer.service.userservice;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.softserve.ukrainer.constant.UkrainerConstant;
import com.softserve.ukrainer.dto.UserDTO;
import com.softserve.ukrainer.entity.Role;
import com.softserve.ukrainer.entity.User;
import com.softserve.ukrainer.exception.UkrainerException;
import com.softserve.ukrainer.repository.UserRepository;
import com.softserve.ukrainer.service.mailservice.MailService;
import com.softserve.ukrainer.utils.UkrainerUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UkrainerUtils ukrainerUtils;
    private final MailService mailService;

    @Override
    public void save(UserDTO userDTO) {
        userRepository.save(User.builder().email(userDTO.getEmail())
                                          .password(passwordEncoder.encode(userDTO.getPassword()))
                                          .name(userDTO.getName())
                                          .roleList(List.of(Role.builder().id(1L).name("USER").build())).build());
    }

    @Override
    public void restorePassword(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail()).orElseThrow( () -> new UkrainerException(UkrainerConstant.USER_NOT_FOUND.getMessage(),500));
        String generatedPassword = ukrainerUtils.generatePassword();
        user.setPassword(passwordEncoder.encode(generatedPassword));
        userRepository.save(user);
        mailService.sendEmail(userDTO, UkrainerConstant.PASSWORD_RESTORE_SUBJECT.getMessage(),
            String.format(UkrainerConstant.PASSWORD_RESTORE_MESSAGE.getMessage(), userDTO.getName(), generatedPassword));
    }
}
