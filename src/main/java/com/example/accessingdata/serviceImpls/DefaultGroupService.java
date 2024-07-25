package com.example.accessingdata.serviceImpls;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.accessingdata.models.GroupAuthor;
import com.example.accessingdata.repositories.GroupRepository;
import com.example.accessingdata.services.GroupService;

@Service
public class DefaultGroupService implements GroupService {

    private final GroupRepository repository;

    public DefaultGroupService(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<GroupAuthor> findAll() {
        List<GroupAuthor> groups = (List<GroupAuthor>) repository.findAll();
        return groups;
    }
    
}
