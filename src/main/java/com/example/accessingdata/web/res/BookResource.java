package com.example.accessingdata.web.res;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessingdata.models.Book;
import com.example.accessingdata.services.Service;

@RestController
@RequestMapping("/resource/books")
public class BookResource {
    private final Service<Long, Book> service;

    public BookResource(Service<Long, Book> service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Book> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable Long id) {
        Book book = service.findById(id);
        return book;
    }
}
