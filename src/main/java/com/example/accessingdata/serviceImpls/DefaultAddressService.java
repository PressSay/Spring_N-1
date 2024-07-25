package com.example.accessingdata.serviceImpls;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.accessingdata.models.Address;
import com.example.accessingdata.repositories.AddressRepository;
import com.example.accessingdata.services.AddressService;

@Service
public class DefaultAddressService implements AddressService {

    private final AddressRepository repository;

    public DefaultAddressService(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Address> findAll() {
        List<Address> addresses = (List<Address>) repository.findAll();
        return addresses;
    }
    
}
