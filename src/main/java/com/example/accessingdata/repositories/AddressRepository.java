package com.example.accessingdata.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.accessingdata.models.Address;

public interface AddressRepository extends CrudRepository<Address, Long>  {}
