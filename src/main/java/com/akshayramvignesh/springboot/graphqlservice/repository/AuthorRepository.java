package com.akshayramvignesh.springboot.graphqlservice.repository;

import com.akshayramvignesh.springboot.graphqlservice.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {}
