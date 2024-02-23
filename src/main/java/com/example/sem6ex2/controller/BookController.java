package com.example.sem6ex2.controller;

import com.example.sem6ex2.model.Book;
import com.example.sem6ex2.model.Reader;
import com.example.sem6ex2.repository.BookRepository;
import com.example.sem6ex2.repository.ReaderRepository;
import com.example.sem6ex2.service.FileGateway;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {
    private final FileGateway fileGateway;
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        fileGateway.writeToFile(book.getTitle() + ".txt", book.toString());
        bookRepository.save(book);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @GetMapping("{id}")
    public Book getById(@PathVariable("id") Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @PutMapping("/{id}/reader/{readerId}")
    public ResponseEntity<Book> assignReaderToBook(@PathVariable Long id, @PathVariable Long readerId) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        Optional<Reader> readerOptional = readerRepository.findById(readerId);

        if (bookOptional.isPresent() && readerOptional.isPresent()) {
            Book book = bookOptional.get();
            Reader reader = readerOptional.get();
            book.setReader(reader);
            bookRepository.save(book);
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            bookRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
