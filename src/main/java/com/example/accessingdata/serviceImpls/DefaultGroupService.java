package com.example.accessingdata.serviceImpls;

import java.util.List;

import org.springframework.stereotype.Service;


import com.example.accessingdata.models.GroupAuthor;
import com.example.accessingdata.repositories.GroupRepository;

@Service
public class DefaultGroupService implements com.example.accessingdata.services.Service<Long, GroupAuthor> {

    private final GroupRepository repository;

    public DefaultGroupService(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<GroupAuthor> findAll() {
        List<GroupAuthor> groups = (List<GroupAuthor>) repository.findAll();
        return groups;
    }

    @Override
    public GroupAuthor findById(Long id) {
        var resGroupAuthor = repository.findById(id);
        var groupAuthor = resGroupAuthor.orElseThrow();
        return groupAuthor;
    }
    
}
