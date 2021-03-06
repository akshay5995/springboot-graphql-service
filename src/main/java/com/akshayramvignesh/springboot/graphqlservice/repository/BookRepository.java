package com.akshayramvignesh.springboot.graphqlservice.repository;

import com.akshayramvignesh.springboot.graphqlservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends JpaRepository<Book, Long> { }
