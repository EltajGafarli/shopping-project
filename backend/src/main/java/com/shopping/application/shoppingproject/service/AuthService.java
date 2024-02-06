package com.shopping.application.shoppingproject.service;

import com.shopping.application.shoppingproject.model.dto.requst.LoginRequest;
import com.shopping.application.shoppingproject.model.dto.requst.RegisterRequest;
import com.shopping.application.shoppingproject.model.dto.response.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    void registration(RegisterRequest registrationRequest);

    void login(LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response);

    UserResponse getUser(UserDetails userDetails);
}
