package com.example.accessingdata.services;

import com.example.accessingdata.models.Library;

import java.util.List;

public interface LibraryService {
    List<Library> findAll();
}
