package com.example.company.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Test configuration providing ObjectMapper for @WebMvcTest slices.
 *
 * Spring Boot 4.0's stricter test slicing doesn't automatically include
 * ObjectMapper in @WebMvcTest contexts, so we provide it explicitly here.
 */
@TestConfiguration
public class TestConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}
