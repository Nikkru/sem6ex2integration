package com.example.sem6ex2.controller;

import com.example.sem6ex2.model.Reader;
import com.example.sem6ex2.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reader")
public class ReaderController {
    private final ReaderRepository repository;

    @GetMapping
    public List<Reader> getAll(){
        return repository.findAll();
    }

    @PostMapping
    public Reader createReader(@RequestBody Reader reader){
        return repository.save(reader);
    }
}
