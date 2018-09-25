package com.akshayramvignesh.springboot.graphqlservice.resolver;

import com.akshayramvignesh.springboot.graphqlservice.model.Author;
import com.akshayramvignesh.springboot.graphqlservice.model.Book;
import com.akshayramvignesh.springboot.graphqlservice.repository.AuthorRepository;
import com.akshayramvignesh.springboot.graphqlservice.repository.BookRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

public class Query implements GraphQLQueryResolver {

    private AuthorRepository authorRepository;

    private BookRepository bookRepository;

    public Query(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Iterable<Author> findAllAuthors() {
        Iterable<Author> authors = authorRepository.findAll();
        return authors;
    }

    public Iterable<Book> findAllBooks() {
        Iterable<Book> books = bookRepository.findAll();
        return books;
    }

    public Long countBooks() {
        return bookRepository.count();
    }

    public Long countAuthors() {
        return authorRepository.count();
    }
}
