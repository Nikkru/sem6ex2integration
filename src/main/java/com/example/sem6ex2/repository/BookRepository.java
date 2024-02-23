package com.example.sem6ex2.repository;

import com.example.sem6ex2.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
