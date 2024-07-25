package com.example.accessingdata.serviceImpls;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.accessingdata.models.Author;
import com.example.accessingdata.repositories.AuthorRepository;
import com.example.accessingdata.services.AuthorService;

@Service
public class DefaultAuthorService implements AuthorService {

    private final AuthorRepository repository;

    public DefaultAuthorService(AuthorRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public List<Author> findAll() {
        List<Author> authors = (List<Author>) repository.findAll();
        return authors;
    }
    
}
