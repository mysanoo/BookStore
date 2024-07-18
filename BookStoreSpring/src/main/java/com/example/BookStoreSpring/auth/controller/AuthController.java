package com.example.BookStoreSpring.auth.controller;

import com.example.BookStoreSpring.auth.dto.SignInRequest;
import com.example.BookStoreSpring.auth.dto.SignUpRequest;
import com.example.BookStoreSpring.auth.service.AuthService;
import com.example.BookStoreSpring.bookStore.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public HttpEntity<ApiResponse> signUp(@RequestBody @Valid SignUpRequest request) {
        ApiResponse apiResponse = authService.signUp(request);
        System.out.println(apiResponse);
        return ResponseEntity.ok(apiResponse);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public HttpEntity<ApiResponse> signIn(@RequestBody @Valid SignInRequest request) {
        ApiResponse apiResponse = authService.signIn(request);
        System.out.println(apiResponse);
        return ResponseEntity.ok(apiResponse);
    }
}
