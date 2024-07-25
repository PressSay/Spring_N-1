package com.example.accessingdata.web.res;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessingdata.models.Book;
import com.example.accessingdata.services.BookService;

@RestController
@RequestMapping("/resource/books")
public class BookResource {
    private final BookService service;

    public BookResource(BookService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Book> getAll() {
        return service.findAll();
    }
}
