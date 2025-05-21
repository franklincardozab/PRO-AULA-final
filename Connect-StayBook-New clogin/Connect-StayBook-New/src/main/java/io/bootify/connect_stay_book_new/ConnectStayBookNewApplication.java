package io.bootify.connect_stay_book_new;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories(basePackages = "io.bootify.connect_stay_book_new.repos")
public class ConnectStayBookNewApplication {
    public static void main(final String[] args) {
        SpringApplication.run(ConnectStayBookNewApplication.class, args);
    }

}
