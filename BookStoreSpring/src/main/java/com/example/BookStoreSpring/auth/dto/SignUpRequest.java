package com.example.BookStoreSpring.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Schema(description = "Registration Request")
public class SignUpRequest {

    @NotBlank(message = "username cannot be empty")
    @Schema(description = "username", example = "Jon")
    private String username;

    @Schema(description = "email", example = "jondoe@gmail.com")
    @NotBlank(message = "email cannot be empty")
    @Email(message = "Email must be format user@example.com")
    private String email;

    @Schema(description = "password", example = "my_1secret1_password")
    @Size(max = 255, message = "password length must be no more than 255 symbols")
    private String password;
}
