package com.felix.Book_Web_Service.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Book {

    private Long id;

    @NotNull(message = "Book name is required")
    @Size(min = 1, max = 100, message = "Book name must be between 1 and 100 characters")
    private String name;

    @NotNull(message = "ISBN is required")
    private String isbn;

    @NotNull(message = "Publish date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "Book type is required")
    private BookType bookType;
}
