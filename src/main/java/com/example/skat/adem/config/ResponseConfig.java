package com.example.skat.adem.config;

import com.example.skat.adem.model.ResponseModel;
import com.example.skat.adem.repository.ResponseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ResponseConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            ResponseRepository responseRepository) {
        return args -> {
            ResponseModel first = new ResponseModel(
                    "success",
                    22
            );

            ResponseModel second = new ResponseModel(
                    "fail",
                    330
            );

            responseRepository.saveAll(List.of(first, second));
        };
    }
}
