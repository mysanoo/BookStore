package com.example.BookStoreSpring.bookStore.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "book")
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String author;

    private String description;

    private BigDecimal price;

    private Integer quantity;

    @OneToOne(fetch = FetchType.EAGER)
    private Attachment bookImage;
}
