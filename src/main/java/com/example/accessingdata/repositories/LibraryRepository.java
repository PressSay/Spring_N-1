package com.example.accessingdata.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.accessingdata.models.Library;

public interface LibraryRepository extends CrudRepository<Library, Long> {}
