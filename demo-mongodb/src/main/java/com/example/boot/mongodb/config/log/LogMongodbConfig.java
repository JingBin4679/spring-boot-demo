package com.example.boot.mongodb.config.log;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Create by bin in 2019/10/28
 **/
@Configuration
@EnableMongoRepositories(includeFilters = {
        @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = {RepoLog.class})},
        basePackages = {"com"},
        mongoTemplateRef = "logMongoTemplate",
        repositoryBaseClass = LogBaseRepositoryImpl.class)
public class LogMongodbConfig {

    @Bean("logMongoProperties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.log")
    public MongoProperties logMongoProperties() {
        return new MongoProperties();
    }


    @Bean("logMongoTemplate")
    public MongoTemplate logMongoTemplate(@Qualifier("logMongoDbFactory") MongoDbFactory mongoDbFactory) {
        return new MongoTemplate(mongoDbFactory);
    }

    @Bean("logMongoDbFactory")
    public MongoDbFactory logMongoDbFactory(@Qualifier("logMongoProperties") MongoProperties mongoProperties) {
        return new SimpleMongoClientDbFactory(mongoProperties.getUri());
    }
}
