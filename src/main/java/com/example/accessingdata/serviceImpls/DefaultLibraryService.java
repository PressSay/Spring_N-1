package com.example.accessingdata.serviceImpls;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.accessingdata.models.Library;
import com.example.accessingdata.repositories.LibraryRepository;
import com.example.accessingdata.services.LibraryService;

@Service
public class DefaultLibraryService implements LibraryService {

    private final LibraryRepository repository;

    public DefaultLibraryService(LibraryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Library> findAll() {
        List<Library> addresses = (List<Library>) repository.findAll();
        return addresses;
    }
    
}
