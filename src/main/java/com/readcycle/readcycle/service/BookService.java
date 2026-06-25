package com.readcycle.readcycle.service;

import com.readcycle.readcycle.dto.BookRequestDTO;
import com.readcycle.readcycle.dto.BookResponseDTO;
import com.readcycle.readcycle.entity.Book;
import com.readcycle.readcycle.exception.ResourceNotFoundException;
import com.readcycle.readcycle.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // CREATE
    public BookResponseDTO createBook(BookRequestDTO request) {

        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setDescription(request.getDescription());
        book.setAvailable(request.getAvailable());

        Book savedBook = bookRepository.save(book);

        return convertToResponseDTO(savedBook);
    }

    // GET ALL
    public List<BookResponseDTO> getAllBooks() {

        List<Book> books = bookRepository.findAll();
        List<BookResponseDTO> responseList = new ArrayList<>();

        for (Book book : books) {
            responseList.add(convertToResponseDTO(book));
        }

        return responseList;
    }

    // GET BY ID
    public BookResponseDTO getBookById(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        return convertToResponseDTO(book);
    }

    // UPDATE
    public BookResponseDTO updateBook(Long id, BookRequestDTO request) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setDescription(request.getDescription());
        book.setAvailable(request.getAvailable());

        Book updatedBook = bookRepository.save(book);

        return convertToResponseDTO(updatedBook);
    }

    // DELETE
    public void deleteBook(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        bookRepository.delete(book);
    }

    // SEARCH BOOKS
    public List<BookResponseDTO> searchBooks(String title) {

        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);
        List<BookResponseDTO> responseList = new ArrayList<>();

        for (Book book : books) {
            responseList.add(convertToResponseDTO(book));
        }

        return responseList;
    }

    // Convert Entity to Response DTO
    private BookResponseDTO convertToResponseDTO(Book book) {

        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getDescription(),
                book.isAvailable()
        );
    }
}