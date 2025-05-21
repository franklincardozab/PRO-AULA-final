package io.bootify.connect_stay_book_new.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("io.bootify.connect_stay_book_new.repos")
public class DomainConfig {
}