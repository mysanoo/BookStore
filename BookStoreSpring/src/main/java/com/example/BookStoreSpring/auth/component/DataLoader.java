package com.example.BookStoreSpring.auth.component;

import com.example.BookStoreSpring.auth.service.UserService;
import com.example.BookStoreSpring.bookStore.entity.Book;
import com.example.BookStoreSpring.bookStore.entity.User;
import com.example.BookStoreSpring.bookStore.entity.enums.UserRole;
import com.example.BookStoreSpring.bookStore.repository.BookRepository;
import com.example.BookStoreSpring.bookStore.service.impl.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final BookServiceImpl bookService;
    private final BookRepository bookRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User admin = User.builder()
                .email("xe1220@gmail.com")
                .userRole(UserRole.ROLE_ADMIN)
                .password(passwordEncoder.encode("admin"))
                .username("admin")
                .build();
        User user = User.builder()
                .email("xe1220@gmail.com")
                .userRole(UserRole.ROLE_USER)
                .password(passwordEncoder.encode("user"))
                .username("user")
                .build();

        userService.save(user);
        userService.save(admin);

        Book book1 = Book.builder()
                .author("Toshmat")
                .description("Tavsiya qilmayman")
                .quantity(3)
                .price(BigDecimal.valueOf(120000))
                .title("O'sha kun")
                .build();

        Book book2 = Book.builder()
                .author("Eshmat")
                .description("Tavsiya qilaman")
                .quantity(5)
                .price(BigDecimal.valueOf(100000))
                .title("O'sha tun")
                .build();
        Book book3 = Book.builder()
                .author("Toshmat")
                .description("Tavsiya qilmayman")
                .quantity(3)
                .price(BigDecimal.valueOf(120000))
                .title("O'sha kun")
                .build();

        Book book4 = Book.builder()
                .author("Eshmat")
                .description("Tavsiya qilaman")
                .quantity(5)
                .price(BigDecimal.valueOf(100000))
                .title("O'sha tun")
                .build();
        Book book5 = Book.builder()
                .author("Toshmat")
                .description("Tavsiya qilmayman")
                .quantity(3)
                .price(BigDecimal.valueOf(120000))
                .title("O'sha kun")
                .build();

        Book book6 = Book.builder()
                .author("Eshmat")
                .description("Tavsiya qilaman")
                .quantity(5)
                .price(BigDecimal.valueOf(100000))
                .title("O'sha tun")
                .build();
        Book book7 = Book.builder()
                .author("Toshmat")
                .description("Tavsiya qilmayman")
                .quantity(3)
                .price(BigDecimal.valueOf(120000))
                .title("O'sha kun")
                .build();

        Book book8 = Book.builder()
                .author("Eshmat")
                .description("Tavsiya qilaman")
                .quantity(5)
                .price(BigDecimal.valueOf(100000))
                .title("O'sha tun")
                .build();

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);
        bookRepository.save(book5);
        bookRepository.save(book6);
    }

}
