package com.akshayramvignesh.springboot.graphqlservice.resolver;

import com.akshayramvignesh.springboot.graphqlservice.model.Author;
import com.akshayramvignesh.springboot.graphqlservice.model.Book;
import com.akshayramvignesh.springboot.graphqlservice.repository.AuthorRepository;
import com.akshayramvignesh.springboot.graphqlservice.repository.BookRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class Mutation implements GraphQLMutationResolver {

    private AuthorRepository authorRepository;

    private BookRepository bookRepository;

    public Mutation(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Author newAuthor(String firstName, String lastName) {
        Author author = authorRepository.save(new Author(firstName, lastName));
        return author;
    }

    public Book newBook(String title, String description, String isbn, Integer pageCount, Long authorId)
    {
        Book book = new Book();
        book.setAuthor(new Author(authorId));
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setDescription(description);
        book.setPageCount(pageCount != null ? pageCount : 0);
        Book savedBook = bookRepository.save(book);

        return savedBook;
    }

    public Long deleteBook(Long id) {
        bookRepository.deleteById(id);
        return id;
    }

    public Book updateBookPageCount(Integer pageCount, Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        Book book = bookOptional.get();
        book.setPageCount(pageCount);
        bookRepository.save(book);
        return book;
    }

}
