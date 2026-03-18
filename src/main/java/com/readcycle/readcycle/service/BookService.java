package com.readcycle.readcycle.service;
import com.readcycle.readcycle.entity.Book;
import com.readcycle.readcycle.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book createBook(Book book){
        return bookRepository.save(book);
    }
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    public Book getBookById(Long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book updateBook(Long id, Book updatedBook ){
        Book book = getBookById(id);
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setDescription(updatedBook.getDescription());
        book.setAvailable(updatedBook.isAvailable());

        return bookRepository.save(book);
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }
}
