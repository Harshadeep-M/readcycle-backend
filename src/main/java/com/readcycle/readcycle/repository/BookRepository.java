package com.readcycle.readcycle.repository;
import com.readcycle.readcycle.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BookRepository extends JpaRepository<Book, Long>{

}
