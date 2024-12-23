package edu.rut_miit.job_station.config;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.GsonBuilder;

@Configuration
public class GsonConfig {
    @Bean
    public GsonBuilder gsonBuilder() {
        var builder = new GsonBuilder();

        builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());

        return builder;
    }
}
