package com.shopping.application.shoppingproject.mapper;


import com.shopping.application.shoppingproject.model.dto.requst.RegisterRequest;
import com.shopping.application.shoppingproject.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User registerRequestToUser(RegisterRequest registerRequest) {

        String password = registerRequest.getPassword();
        String encodedPassword = passwordEncoder.encode(password);

        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(encodedPassword)
                .phone(registerRequest.getPhoneNumber())
                .address(registerRequest.getAddress())
                .build();

        return user;
    }
}
