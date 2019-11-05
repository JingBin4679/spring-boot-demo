package com.example.boot.mongodb.log;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Create by bin in 2019/11/5
 **/
@Document(collection = "log")
public class DbLog {

    private String logId;
    private String operator;
    private String content;

    public DbLog(String logId, String operator, String content) {
        this.logId = logId;
        this.operator = operator;
        this.content = content;
    }

    public DbLog() {
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
