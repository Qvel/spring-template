package com.softserve.ukrainer.service.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.softserve.ukrainer.constant.UkrainerConstant;
import com.softserve.ukrainer.entity.User;
import com.softserve.ukrainer.exception.UkrainerException;
import com.softserve.ukrainer.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        log.debug("UserDetailsServiceImpl.loadUserByUsername = {}" + email);

        User user = userRepository.findByEmail(email).orElseThrow( () -> new UkrainerException(UkrainerConstant.USER_NOT_FOUND.getMessage(), 500));

        return new CustomUserDetails(user);
    }
}
