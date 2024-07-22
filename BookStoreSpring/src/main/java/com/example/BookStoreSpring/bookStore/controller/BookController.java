package com.example.BookStoreSpring.bookStore.controller;

import com.example.BookStoreSpring.bookStore.dto.BookDto;
import com.example.BookStoreSpring.bookStore.entity.Attachment;
import com.example.BookStoreSpring.bookStore.service.impl.AttachmentServiceImpl;
import com.example.BookStoreSpring.bookStore.service.impl.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookServiceImpl bookService;
    private final AttachmentServiceImpl attachmentService;


    @PostMapping()
    HttpEntity<?> saveBook(@RequestBody BookDto bookDto){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.create(bookDto));
    }

    @DeleteMapping("/{bookId}")
    HttpEntity<?> deleteBook(@PathVariable Integer bookId){
        return ResponseEntity.ok(bookService.delete(bookId));
    }

    @PutMapping("/{bookId}")
    HttpEntity<?> updateBook(@RequestBody BookDto bookDto, @PathVariable Integer bookId){
        return ResponseEntity.ok(bookService.update(bookDto, bookId));
    }

    @GetMapping
    HttpEntity<?> getAll(){
        return ResponseEntity.ok(bookService.allBooks());
    }

    @GetMapping("/search")
    HttpEntity<?> search(@RequestParam String title){
        return ResponseEntity.ok(bookService.search(title));
    }
}
