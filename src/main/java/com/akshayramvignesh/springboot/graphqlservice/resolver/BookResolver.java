package com.akshayramvignesh.springboot.graphqlservice.resolver;

import com.akshayramvignesh.springboot.graphqlservice.model.Author;
import com.akshayramvignesh.springboot.graphqlservice.model.Book;
import com.akshayramvignesh.springboot.graphqlservice.repository.AuthorRepository;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;

public class BookResolver implements GraphQLResolver<Book> {

    @Autowired
    private AuthorRepository authorRepository;

    public BookResolver(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    public Author getAuthor(Book book) {
        return authorRepository.findById(book.getAuthor().getId()).get();
    }
}
