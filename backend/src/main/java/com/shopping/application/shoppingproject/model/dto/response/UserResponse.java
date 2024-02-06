package com.shopping.application.shoppingproject.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
}
