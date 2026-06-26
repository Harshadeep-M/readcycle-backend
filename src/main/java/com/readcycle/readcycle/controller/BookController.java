package com.readcycle.readcycle.controller;

import com.readcycle.readcycle.dto.BookRequestDTO;
import com.readcycle.readcycle.dto.BookResponseDTO;
import com.readcycle.readcycle.dto.PaginationResponseDTO;
import com.readcycle.readcycle.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    // GET ALL WITH PAGINATION
    @GetMapping
    public PaginationResponseDTO<BookResponseDTO> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return bookService.getAllBooks(page, size, sortBy, direction);
    }

    // SEARCH BOOKS WITH PAGINATION
    // SEARCH BOOKS WITH PAGINATION + SORTING
    @GetMapping("/search")
    public PaginationResponseDTO<BookResponseDTO> searchBooks(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return bookService.searchBooks(title, page, size, sortBy, direction);
    }
    // GET BOOK BY ID
    @GetMapping("/{id:\\d+}")
    public BookResponseDTO getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public BookResponseDTO updateBook(
            @PathVariable Long id,
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