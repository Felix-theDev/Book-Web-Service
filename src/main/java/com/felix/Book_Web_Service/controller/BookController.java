package com.felix.Book_Web_Service.controller;
import com.felix.Book_Web_Service.model.Book;
import com.felix.Book_Web_Service.service.ManagementApiClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final ManagementApiClient apiClient;


    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", apiClient.getAllBooks());
        return "books";
    }

    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "add-book";  // Return to the form with error messages
        }
        apiClient.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        Book book = apiClient.getBookById(id);
        // Format the date to ISO format (YYYY-MM-DD)
        book.setPublishDate(book.getPublishDate());
        model.addAttribute("book", book);
        System.out.println(model.getAttribute("book"));
        return "edit-book";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, @Valid @ModelAttribute Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-book";  // Return to the form with error messages
        }
        apiClient.updateBook(id, book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        apiClient.deleteBook(id);
        return "redirect:/books";
    }
}
