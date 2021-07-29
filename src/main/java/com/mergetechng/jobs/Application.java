package com.mergetechng.jobs;

import io.github.kaiso.relmongo.config.EnableRelMongo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableMongoRepositories(value = "com.mergetechng.jobs.repositories")
@EnableScheduling
@EnableWebMvc
@SpringBootApplication
//@EnableRelMongo
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
