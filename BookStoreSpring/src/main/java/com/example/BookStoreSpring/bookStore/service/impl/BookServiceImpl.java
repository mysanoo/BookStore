package com.example.BookStoreSpring.bookStore.service.impl;


import com.example.BookStoreSpring.bookStore.entity.Book;
import com.example.BookStoreSpring.bookStore.repository.BookRepository;
import com.example.BookStoreSpring.bookStore.service.BookService;
import com.example.BookStoreSpring.bookStore.dto.ApiResponse;
import com.example.BookStoreSpring.bookStore.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public ApiResponse create(BookDto bookCreateDto) {
        Book book = Book.builder()
                .title(bookCreateDto.getTitle())
                .author(bookCreateDto.getAuthor())
                .description(bookCreateDto.getDescription())
                .quantity(bookCreateDto.getQuantity())
                .price(bookCreateDto.getPrice())
                .bookImage(bookCreateDto.getAttachment())
                .build();
        bookRepository.save(book);
        return ApiResponse.builder().message("success").success(true).data(book).build();
    }

    @Override
    public ApiResponse update(BookDto bookDto, Integer bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isPresent()){
            book.get().setAuthor(bookDto.getAuthor());
            book.get().setDescription(bookDto.getDescription());
            book.get().setQuantity(bookDto.getQuantity());
            book.get().setTitle(bookDto.getTitle());
            book.get().setPrice(bookDto.getPrice());
            bookRepository.save(book.get());
            return ApiResponse.builder().data(book.get()).success(true).message("success").build();
        }else return ApiResponse.builder().message("Not Found").success(false).build();
    }



    @Override
    public ApiResponse delete(Integer bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isPresent()){
            bookRepository.delete(book.get());
            return ApiResponse.builder().success(true).data(book.get()).build();
        }else return ApiResponse.builder().message("Not Found").success(false).build();
    }

    @Override
    public ApiResponse allBooks() {
        return ApiResponse.builder()
                .data(bookRepository.findAll())
                .success(true)
                .build();
    }

    @Override
    public ApiResponse search(String title) {
        List<Book> books = bookRepository.search(title);
        return ApiResponse.builder()
                .data(books)
                .success(true)
                .build();
    }
}
