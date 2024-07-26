package com.example.accessingdata.web.res;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessingdata.models.Library;
import com.example.accessingdata.services.Service;

@RestController
@RequestMapping("/resource/libraries")
public class LibraryResource {
    private final Service<Long, Library> service;

    public LibraryResource(Service<Long, Library>  service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Library> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Library findById(@PathVariable Long id) {
        Library library = service.findById(id);
        return library;
    }
}
