package com.example.accessingdata.web.res;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessingdata.models.Author;
import com.example.accessingdata.services.AuthorService;

@RestController
@RequestMapping("/resource/authors")
public class AuthorResource {
    private final AuthorService service;

    public AuthorResource(AuthorService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Author> getAll() {
        return service.findAll();
    }
}
