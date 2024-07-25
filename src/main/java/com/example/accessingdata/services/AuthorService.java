package com.example.accessingdata.services;

import com.example.accessingdata.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
