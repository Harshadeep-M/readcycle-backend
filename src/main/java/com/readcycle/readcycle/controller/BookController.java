package com.readcycle.readcycle.controller;

import com.readcycle.readcycle.dto.BookRequestDTO;
import com.readcycle.readcycle.dto.BookResponseDTO;
import com.readcycle.readcycle.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // CREATE
    @PostMapping
    public BookResponseDTO createBook(@Valid @RequestBody BookRequestDTO request) {
        return bookService.createBook(request);
    }

    // GET ALL
    @GetMapping
    public List<BookResponseDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    // SEARCH BOOKS
    @GetMapping("/search")
    public List<BookResponseDTO> searchBooks(@RequestParam String title) {
        return bookService.searchBooks(title);
    }

    // GET BY ID
    @GetMapping("/{id:\\d+}")
    public BookResponseDTO getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public BookResponseDTO updateBook(@PathVariable Long id,
                                      @Valid @RequestBody BookRequestDTO request) {
        return bookService.updateBook(id, request);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "Book deleted successfully";
    }
}