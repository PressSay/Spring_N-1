package com.example.accessingdata.web.res;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessingdata.models.GroupAuthor;
import com.example.accessingdata.services.GroupService;

import java.util.List;

@RestController
@RequestMapping("/resource/groups")
public class GroupResource {
    private final GroupService service;

    public GroupResource(GroupService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<GroupAuthor> getAll() {
        List<GroupAuthor> groups = service.findAll();
        System.err.println(groups);
        return service.findAll();
    }
}
