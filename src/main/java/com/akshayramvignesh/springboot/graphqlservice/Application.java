package com.akshayramvignesh.springboot.graphqlservice;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	private Environment env;

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(Application.class);
		Environment env = app.run(args).getEnvironment();
		logger.info("\n----------------------------------------------------------\n\t" +
						"Application '{}' is running! Access URLs:\n\t" +
						"Local: \t\thttp://127.0.0.1:{}\n\t" +
						"External: \thttp://{}:{}\n----------------------------------------------------------",
				env.getProperty("spring.application.name"),
				env.getProperty("server.port"),
				InetAddress.getLocalHost().getHostAddress(),
				env.getProperty("server.port"));
	}

}
