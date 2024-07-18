package com.example.BookStoreSpring.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Authentication Request")
public class SignInRequest {

    @NotBlank(message = "username cannot be empty")
    private String username;

    @NotBlank(message = "password cannot be empty")
    private String password;
}
