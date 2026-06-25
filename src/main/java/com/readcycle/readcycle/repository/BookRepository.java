package com.readcycle.readcycle.repository;

import com.readcycle.readcycle.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Search books by title
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);

}