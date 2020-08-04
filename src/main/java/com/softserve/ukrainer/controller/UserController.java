package com.softserve.ukrainer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.ukrainer.constant.UkrainerConstant;
import com.softserve.ukrainer.dto.JwtDTO;
import com.softserve.ukrainer.dto.UserDTO;
import com.softserve.ukrainer.exception.UkrainerException;
import com.softserve.ukrainer.service.security.JwtTokenService;
import com.softserve.ukrainer.service.userservice.UserService;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/users")
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/ping", produces = "application/json")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> saveUser(@RequestBody UserDTO user) {
        userService.save(user);
        return ResponseEntity.ok(String.format(UkrainerConstant.REGISTERED_SUCCESSFULLY.getMessage(), user.getEmail()));
    }

    @RequestMapping(value = "/auth/token", method = RequestMethod.POST)
    public ResponseEntity<JwtDTO> createAuthenticationToken(@RequestBody UserDTO authenticationRequest) {

        final Authentication auth = authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = jwtTokenService.generateToken(auth);

        return ResponseEntity.ok(new JwtDTO(token, jwtTokenService.getClaimFromToken(token, Claims:: getExpiration)));
    }

    private Authentication authenticate(String username, String password) {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            throw new UkrainerException(UkrainerConstant.AUTHENTICATION_EXCEPTION.getMessage(),500);
        }
    }
}
