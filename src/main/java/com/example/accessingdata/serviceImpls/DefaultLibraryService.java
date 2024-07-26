package com.example.accessingdata.serviceImpls;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.accessingdata.models.Library;
import com.example.accessingdata.repositories.LibraryRepository;

@Service
public class DefaultLibraryService implements com.example.accessingdata.services.Service<Long, Library> {

    private final LibraryRepository repository;

    public DefaultLibraryService(LibraryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Library> findAll() {
        List<Library> addresses = (List<Library>) repository.findAll();
        return addresses;
    }

    @Override
    public Library findById(Long id) {
        var resLibrary = repository.findById(id);
        var Library = resLibrary.orElseThrow();
        return Library;
    }
    
}
