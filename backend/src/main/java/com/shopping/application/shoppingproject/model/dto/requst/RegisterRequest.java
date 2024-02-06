package com.shopping.application.shoppingproject.model.dto.requst;

import com.shopping.application.shoppingproject.validation.constraint.validator.EmailValidation;
import com.shopping.application.shoppingproject.validation.constraint.validator.PasswordValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequest {

    @NotNull(message = "field cant be null")
    @NotBlank(message = "field cant be blank")
    @NotEmpty(message = "field cant be empty")
    private String firstName;


    @NotNull(message = "field cant be null")
    @NotBlank(message = "field cant be blank")
    @NotEmpty(message = "field cant be empty")
    private String lastName;


    @EmailValidation
    private String email;


    @PasswordValidation
    private String password;


    @NotNull(message = "field cant be null")
    @NotBlank(message = "field cant be blank")
    @NotEmpty(message = "field cant be empty")
    private String phoneNumber;


    @NotNull(message = "field cant be null")
    @NotBlank(message = "field cant be blank")
    @NotEmpty(message = "field cant be empty")
    private String address;

}
