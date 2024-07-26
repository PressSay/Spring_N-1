package com.example.accessingdata.serviceImpls;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.accessingdata.models.Address;
import com.example.accessingdata.repositories.AddressRepository;

@Service
public class DefaultAddressService implements com.example.accessingdata.services.Service<Long, Address> {

    private final AddressRepository repository;

    public DefaultAddressService(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Address> findAll() {
        List<Address> addresses = (List<Address>) repository.findAll();
        return addresses;
    }

    @Override
    public Address findById(Long id) {
        var resAddress = repository.findById(id);
        var address = resAddress.orElseThrow();
        return address;
    }
    
}
