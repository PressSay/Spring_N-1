package com.example.accessingdata.serviceImpls;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.accessingdata.models.Book;
import com.example.accessingdata.repositories.BookRepository;
import com.example.accessingdata.services.BookService;

@Service
public class DefaultBookService implements BookService {

    private final BookRepository repository;

    public DefaultBookService(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = (List<Book>) repository.findAll();
        return books;
    }

}
