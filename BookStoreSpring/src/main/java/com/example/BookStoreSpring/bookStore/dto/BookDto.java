package com.example.BookStoreSpring.bookStore.dto;

import com.example.BookStoreSpring.bookStore.entity.Attachment;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {
    @NotNull(message = "Title is required")
    private String title;
    @NotNull(message = "Description  is required")
    private String description;
    @NotBlank(message = "Author name must not be empty")
    private String author;
    @NotNull(message = "Price must not be empty")
    @Min(value = 0, message = "Price must be a non-negative number")
    private BigDecimal price;
    @Min(value = 0, message = "Price must be a non-negative number")
    private Integer quantity;
    private Attachment attachment;
}
