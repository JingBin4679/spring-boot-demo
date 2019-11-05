package com.example.boot;


import com.example.boot.mongodb.log.DbLog;
import com.example.boot.mongodb.log.LogRepository;
import com.example.boot.mongodb.user.DbUser;
import com.example.boot.mongodb.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoMongoDBApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LogRepository logRepository;


    @Test
    public void testContextLoads() {
        assertTrue(true);
    }

    @Test
    public void testRepoData() {
        DbUser dbUser = new DbUser("12345", "Hello", 19);
        userRepository.save(dbUser);

        assertTrue(userRepository.count() >= 1);
    }

    @Test
    public void testRepoLog() {
        DbLog dbLog = new DbLog(UUID.randomUUID().toString(), "Hello", "create user");
        logRepository.save(dbLog);

        assertTrue(logRepository.count() >= 1);
    }

}
