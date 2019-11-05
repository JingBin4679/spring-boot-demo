package com.example.boot.mongodb.config.data;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
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
                classes = {RepoData.class})},
        basePackages = {"com"},
        mongoTemplateRef = "dataMongoTemplate",
        repositoryBaseClass = DataBaseRepositoryImpl.class)
public class DataMongodbConfig {

    @Bean("dataMongoProperties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.data")
    @Primary
    public MongoProperties dataMongoProperties() {
        return new MongoProperties();
    }


    @Bean("dataMongoTemplate")
    @Primary
    public MongoTemplate dataMongoTemplate(@Qualifier("dataMongoDbFactory") MongoDbFactory mongoDbFactory) {
        return new MongoTemplate(mongoDbFactory);
    }

    @Bean("dataMongoDbFactory")
    @Primary
    public MongoDbFactory dataMongoDbFactory(@Qualifier("dataMongoProperties") MongoProperties mongoProperties) {
        return new SimpleMongoClientDbFactory(mongoProperties.getUri());
    }
}
