package com.example.BookStoreSpring.bookStore.repository;

import com.example.BookStoreSpring.bookStore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT b FROM Book b WHERE " +
            "(b.title LIKE %:title%) OR " +
            "(b.author LIKE %:title%)")
    List<Book> search(@Param("title") String title);
}
