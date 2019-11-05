package com.example.boot.mongodb.config.log;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Create by bin in 2019/10/28
 **/
@NoRepositoryBean
public interface LogBaseRepository<T, ID> extends MongoRepository<T, ID> {
}
