package com.example.accessingdata.web.res;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessingdata.models.Author;
import com.example.accessingdata.services.Service;

@RestController
@RequestMapping("/resource/authors")
public class AuthorResource {
    private final Service<Long, Author>  service;

    public AuthorResource(Service<Long, Author> service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Author> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Author findById(@PathVariable Long id) {
        Author author = service.findById(id);
        return author;
    }
}
