package com.example.accessingdata.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.accessingdata.models.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {}
