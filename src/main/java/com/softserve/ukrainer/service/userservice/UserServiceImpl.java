package com.softserve.ukrainer.service.userservice;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.softserve.ukrainer.dto.UserDTO;
import com.softserve.ukrainer.entity.Role;
import com.softserve.ukrainer.entity.User;
import com.softserve.ukrainer.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void save(UserDTO userDTO) {
        userRepository.save(User.builder().email(userDTO.getEmail())
                                          .password(passwordEncoder.encode(userDTO.getPassword()))
                                          .name(userDTO.getName())
                                          .roleList(List.of(Role.builder().id(1L).name("USER").build())).build());
    }
}
