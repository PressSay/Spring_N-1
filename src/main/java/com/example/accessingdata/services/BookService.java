package com.example.accessingdata.services;


import com.example.accessingdata.models.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
}
