package com.example.accessingdata.serviceImpls;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.accessingdata.models.Book;
import com.example.accessingdata.repositories.BookRepository;

@Service
public class DefaultBookService implements com.example.accessingdata.services.Service<Long, Book> {

    private final BookRepository repository;

    public DefaultBookService(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = (List<Book>) repository.findAll();
        return books;
    }

    @Override
    public Book findById(Long id) {
        var resBook = repository.findById(id);
        var book = resBook.orElseThrow();
        return book;
    }

}
