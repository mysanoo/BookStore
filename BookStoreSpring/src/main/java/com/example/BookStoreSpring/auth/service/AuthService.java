package com.example.BookStoreSpring.auth.service;

import com.example.BookStoreSpring.auth.dto.JwtAuthenticationResponse;
import com.example.BookStoreSpring.auth.dto.SignInRequest;
import com.example.BookStoreSpring.auth.dto.SignUpRequest;
import com.example.BookStoreSpring.bookStore.dto.ApiResponse;
import com.example.BookStoreSpring.bookStore.entity.User;
import com.example.BookStoreSpring.bookStore.entity.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public ApiResponse signUp(SignUpRequest request) {

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRole(UserRole.ROLE_USER)
                .build();

        userService.create(user);

        var jwt = jwtService.generateToken(user);
        return ApiResponse.builder().data(jwt).success(true).build();
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public ApiResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        UserDetails user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());


        String jwt = jwtService.generateToken(user);
        return ApiResponse.builder().data(user).message(jwt).success(true).build();
    }
}
