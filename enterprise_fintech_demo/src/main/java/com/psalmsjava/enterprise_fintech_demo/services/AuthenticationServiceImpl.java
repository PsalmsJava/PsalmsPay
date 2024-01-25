package com.psalmsjava.enterprise_fintech_demo.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.psalmsjava.enterprise_fintech_demo.dao.requests.SignInRequest;
import com.psalmsjava.enterprise_fintech_demo.dao.requests.SignUpRequest;
import com.psalmsjava.enterprise_fintech_demo.dao.response.JwtAuthenticationResponse;
import com.psalmsjava.enterprise_fintech_demo.entities.UserEntity;
import com.psalmsjava.enterprise_fintech_demo.enums.Role;
import com.psalmsjava.enterprise_fintech_demo.interfaces.AuthenticationService;
import com.psalmsjava.enterprise_fintech_demo.interfaces.JwtService;
import com.psalmsjava.enterprise_fintech_demo.interfaces.SignInRequest;
import com.psalmsjava.enterprise_fintech_demo.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = UserEntity.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
