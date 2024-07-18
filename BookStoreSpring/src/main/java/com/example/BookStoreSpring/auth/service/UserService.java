package com.example.BookStoreSpring.auth.service;

import com.example.BookStoreSpring.bookStore.entity.User;
import com.example.BookStoreSpring.bookStore.entity.enums.UserRole;
import com.example.BookStoreSpring.bookStore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User save(User user) {
       return repository.save(user);
    }

    public User create(User user){
        if(repository.existsByUsername(user.getUsername())){
            throw new RuntimeException("a user same name already exists");
        }
        if(repository.existsByEmail(user.getEmail())){
            throw new RuntimeException("a user same email already exists");
        }

        return repository.save(user);
    }

    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setUserRole(UserRole.ROLE_ADMIN);
        save(user);
    }
}
