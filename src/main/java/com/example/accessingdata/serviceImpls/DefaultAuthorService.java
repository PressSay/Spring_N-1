package com.example.accessingdata.serviceImpls;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.accessingdata.models.Author;
import com.example.accessingdata.repositories.AuthorRepository;

@Service
public class DefaultAuthorService implements com.example.accessingdata.services.Service<Long, Author> {

    private final AuthorRepository repository;

    public DefaultAuthorService(AuthorRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public List<Author> findAll() {
        List<Author> authors = (List<Author>) repository.findAll();
        return authors;
    }

    @Override
    public Author findById(Long id) {
        var resAuthor = repository.findById(id);
        var author = resAuthor.orElseThrow();
        return author;
    }
    
}
