package org.eclipse.jakarta.hello.base.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


public class Mapper {

    private final ObjectMapper mapper;

    public Mapper() {
        this.mapper = new ObjectMapper();
        configureObjectMapper();
    }

    private void configureObjectMapper() {
        // Register the JavaTimeModule with the ObjectMapper
        mapper.registerModule(new JavaTimeModule());
        // You can add more configuration options if needed
        // For example, to write dates in a specific format:
        // objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
    }

    public ObjectMapper getObjectMapper() {
        return mapper;
    }
}

