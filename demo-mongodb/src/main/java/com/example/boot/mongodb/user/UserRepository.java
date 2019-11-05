package com.example.boot.mongodb.user;

import com.example.boot.mongodb.config.data.DataBaseRepository;
import com.example.boot.mongodb.config.data.RepoData;

/**
 * Create by bin in 2019/11/5
 **/
@RepoData
public interface UserRepository extends DataBaseRepository<DbUser, String> {
}
