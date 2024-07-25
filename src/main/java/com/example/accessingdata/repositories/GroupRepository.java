package com.example.accessingdata.repositories;

import com.example.accessingdata.models.GroupAuthor;

import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<GroupAuthor, Long> {}
