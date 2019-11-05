package com.example.boot.mongodb.config.log;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Create by bin in 2019/10/28
 **/
@NoRepositoryBean
public class LogBaseRepositoryImpl<T, ID> extends SimpleMongoRepository<T, ID> implements LogBaseRepository<T, ID> {
    private final MongoOperations mongoTemplate;
    private final MongoEntityInformation<T, ID> entityInformation;
    private final Class<T> clazz;

    /**
     * Creates a new {@link SimpleMongoRepository} for the given {@link MongoEntityInformation} and {@link MongoTemplate}.
     *
     * @param metadata        must not be {@literal null}.
     * @param mongoOperations must not be {@literal null}.
     */
    public LogBaseRepositoryImpl(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
        this.mongoTemplate=mongoOperations;
        this.entityInformation = metadata;
        clazz = entityInformation.getJavaType();
    }
}
