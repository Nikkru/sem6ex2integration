package com.example.sem6ex2.controller;

import com.example.sem6ex2.model.Reader;
import com.example.sem6ex2.repository.ReaderRepository;
import com.example.sem6ex2.service.FileGateway;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reader")
public class ReaderController {

    private final FileGateway fileGateway;
    private final ReaderRepository repository;

    @GetMapping
    public List<Reader> getAll(){
        return repository.findAll();
    }

    /**
     *
     * @param reader экземпляр класса который создается
     * @return созданный экземпляр класса
     */
    @PostMapping
    public ResponseEntity<Reader> createReader(@RequestBody Reader reader){
        repository.save(reader);
        fileGateway.writeToFile("id_"
                + reader.getId()
                + "_name_"
                + reader.getName()
                + ".txt", reader.toString());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
