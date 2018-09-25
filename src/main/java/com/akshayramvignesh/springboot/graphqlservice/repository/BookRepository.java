package com.akshayramvignesh.springboot.graphqlservice.repository;

import com.akshayramvignesh.springboot.graphqlservice.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> { }
