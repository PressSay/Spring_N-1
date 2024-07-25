package com.example.accessingdata.web.res;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessingdata.models.Address;
import com.example.accessingdata.services.AddressService;

import java.util.List;

@RestController
@RequestMapping("/resource/addresses")
public class AddressResource {
    
    private final AddressService service;

    public AddressResource(AddressService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Address> getAll() {
        return service.findAll();
    }

}
