package com.example.accessingdata.services;

import java.util.List;


public interface Service<I, M> {
    List<M> findAll();
    M findById(I id);
}
