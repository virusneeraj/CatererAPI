package com.hunza.catererapi;

import com.hunza.catererapi.model.CatererDocument;
import com.hunza.catererapi.repository.CatererRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class CatererApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatererApiApplication.class, args);
    }

}
