package com.akshayramvignesh.springboot.graphqlservice.config;

import com.akshayramvignesh.springboot.graphqlservice.model.Author;
import com.akshayramvignesh.springboot.graphqlservice.model.Book;
import com.akshayramvignesh.springboot.graphqlservice.repository.AuthorRepository;
import com.akshayramvignesh.springboot.graphqlservice.repository.BookRepository;
import com.akshayramvignesh.springboot.graphqlservice.resolver.BookResolver;
import com.akshayramvignesh.springboot.graphqlservice.resolver.Mutation;
import com.akshayramvignesh.springboot.graphqlservice.resolver.Query;
import com.akshayramvignesh.springboot.graphqlservice.web.errors.GraphQLErrorAdapter;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class GraphQLConfiguraion {

    @Bean
    public GraphQLErrorHandler errorHandler() {
        return new GraphQLErrorHandler() {
            @Override
            public List<GraphQLError> processErrors(List<GraphQLError> errors) {
                List<GraphQLError> clientErrors = errors.stream()
                        .filter(this::isClientError)
                        .collect(Collectors.toList());

                List<GraphQLError> serverErrors = errors.stream()
                        .filter(e -> !isClientError(e))
                        .map(GraphQLErrorAdapter::new)
                        .collect(Collectors.toList());

                List<GraphQLError> e = new ArrayList<>();
                e.addAll(clientErrors);
                e.addAll(serverErrors);
                return e;
            }

            protected boolean isClientError(GraphQLError error) {
                return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
            }
        };
    }

    @Bean
    public BookResolver authorResolver(AuthorRepository authorRepository) {
        return new BookResolver(authorRepository);
    }

    @Bean
    public Query query(AuthorRepository authorRepository, BookRepository bookRepository) {
        return new Query(authorRepository, bookRepository);
    }

    @Bean
    public Mutation mutation(AuthorRepository authorRepository, BookRepository bookRepository) {
        return new Mutation(authorRepository, bookRepository);
    }


    @Bean
    public CommandLineRunner demo(AuthorRepository authorRepository, BookRepository bookRepository) {
        return (args) -> {
            Author author = new Author("Sam", "Newman");
            authorRepository.save(author);

            bookRepository.save(new Book("Building Microservices", "This book talks about the simplest way of approaching microservices architecture", "0071809252", 280, author));
        };
    }

}
