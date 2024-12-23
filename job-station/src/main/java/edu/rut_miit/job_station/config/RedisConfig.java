package edu.rut_miit.job_station.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

import com.google.gson.GsonBuilder;

@Configuration
public class RedisConfig {
    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private int redisPort;

    @Autowired
    private GsonBuilder gsonBuilder;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisHost, redisPort);

        return new LettuceConnectionFactory(configuration);
    }

    @Bean
    public RedisCacheManager cacheManager() {
        // RedisCacheConfiguration cacheConfig = myDefaultCacheConfig(Duration.ofMinutes(10)).disableCachingNullValues();

        return RedisCacheManager.builder(redisConnectionFactory())
                // .cacheDefaults(cacheConfig)
                // .withCacheConfiguration("vacancies", myDefaultCacheConfig(Duration.ofMinutes(10)))
                // .withCacheConfiguration("top_user_vacancies", myDefaultCacheConfig(Duration.ofMinutes(10)))
                // .withCacheConfiguration("resumes", myDefaultCacheConfig(Duration.ofMinutes(10)))
                .build();
    }

    // private RedisCacheConfiguration myDefaultCacheConfig(Duration duration) {
    //     return RedisCacheConfiguration
    //             .defaultCacheConfig()
    //             .entryTtl(duration);
    //             // .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    // }

    // @Bean
    // public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
    //     return RedisCacheManager.create(connectionFactory);
    // }
}
