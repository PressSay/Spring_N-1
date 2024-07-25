package com.example.accessingdata.services;


import com.example.accessingdata.models.Address;

import java.util.List;

public interface AddressService {
    List<Address> findAll();
}
