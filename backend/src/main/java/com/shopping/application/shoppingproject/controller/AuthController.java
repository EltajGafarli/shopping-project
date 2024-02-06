package com.shopping.application.shoppingproject.controller;


import com.shopping.application.shoppingproject.model.dto.requst.LoginRequest;
import com.shopping.application.shoppingproject.model.dto.requst.RegisterRequest;
import com.shopping.application.shoppingproject.model.dto.response.UserResponse;
import com.shopping.application.shoppingproject.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest dto) {
        authService.registration(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Registered Successfully");
    }

    @PostMapping(path = {"/login", "/login/"})
    public ResponseEntity<?> login(@RequestBody LoginRequest dto, HttpServletRequest request, HttpServletResponse response) {
        authService.login(dto, request, response);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Login");
    }

    @GetMapping(path = "/user")
    @PreAuthorize(value = "hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserResponse> getUser(@AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.authService.getUser(userDetails));
    }


}
