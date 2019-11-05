package com.example.boot.mongodb.log;

import com.example.boot.mongodb.config.log.LogBaseRepository;
import com.example.boot.mongodb.config.log.RepoLog;

/**
 * Create by bin in 2019/11/5
 **/
@RepoLog
public interface LogRepository extends LogBaseRepository<DbLog, String> {
}
