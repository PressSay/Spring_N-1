package com.example.accessingdata.web.res;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessingdata.models.Library;
import com.example.accessingdata.services.LibraryService;

@RestController
@RequestMapping("/resource/libraries")
public class LibraryResource {
    private final LibraryService service;

    public LibraryResource(LibraryService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Library> getAll() {
        return service.findAll();
    }
}
