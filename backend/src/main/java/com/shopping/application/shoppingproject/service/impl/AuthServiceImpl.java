package com.shopping.application.shoppingproject.service.impl;

import com.shopping.application.shoppingproject.error.EmailDuplicateException;
import com.shopping.application.shoppingproject.mapper.UserMapper;
import com.shopping.application.shoppingproject.model.dto.requst.LoginRequest;
import com.shopping.application.shoppingproject.model.dto.requst.RegisterRequest;
import com.shopping.application.shoppingproject.model.dto.response.UserResponse;
import com.shopping.application.shoppingproject.model.entity.User;
import com.shopping.application.shoppingproject.model.enums.UserRole;
import com.shopping.application.shoppingproject.repository.UserRepository;
import com.shopping.application.shoppingproject.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final SecurityContextRepository securityContextRepository;

    private final UserMapper userMapper;

    @Value("${admin.email}")
    private String adminEmail;

    @Transactional
    @Override
    public void registration(RegisterRequest registrationRequest) {
        emailDuplicatingChecking(registrationRequest);
        User user = userRoleChecking(registrationRequest);
        userRepository.save(user);
    }

    @Override
    public void login(LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication(loginRequest));

        securityContextRepository.saveContext(context, request, response);
    }

    @Override
    public UserResponse getUser(UserDetails userDetails) {
        User user = this.userRepository.findByEmail(
                        userDetails.getUsername()
                )
                .orElseThrow(
                        () -> new IllegalStateException("User not found")
                );

        return UserResponse
                .builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .address(user.getAddress())
                .email(user.getEmail())
                .build();
    }

    public void emailDuplicatingChecking(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new EmailDuplicateException("You cannot create a new user with the same email.");
        }
    }

    public User userRoleChecking(RegisterRequest registerRequest) {
        User user = userMapper.registerRequestToUser(registerRequest);
        if (adminEmail.equals(registerRequest.getEmail())) {
            user.setUserRole(UserRole.ADMIN);
            return user;
        }
        user.setUserRole(UserRole.USER);
        return user;
    }

    private Authentication authentication(LoginRequest loginRequest) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    }
}
