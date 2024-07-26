package com.example.accessingdata.web.res;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessingdata.models.Address;
import com.example.accessingdata.services.Service;

import java.util.List;


@RestController
@RequestMapping("/resource/addresses")
public class AddressResource {
    
    private final Service<Long, Address> service;

    public AddressResource(Service<Long, Address> service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Address> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Address findById(@PathVariable Long id) {
        Address address = service.findById(id);
        return address;
    }

}
