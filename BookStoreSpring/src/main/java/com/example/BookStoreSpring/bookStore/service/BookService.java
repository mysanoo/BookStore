package com.example.BookStoreSpring.bookStore.service;

import com.example.BookStoreSpring.bookStore.dto.ApiResponse;
import com.example.BookStoreSpring.bookStore.dto.BookDto;

public interface BookService {

    ApiResponse create(BookDto bookCreateDto);

    ApiResponse update(BookDto bookDto, Integer bookId);

    ApiResponse delete(Integer bookId);

    ApiResponse allBooks();

    ApiResponse search(String title);
}
