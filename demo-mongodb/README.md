本例集成了MongoDB多数据源
### 配置

    spring:
      data:
        mongodb:
          data: # data数据源
            uri: mongodb://localhost:27017/data
          log:  # log数据源
            uri: mongodb://localhost:27017/log

### 定义对应Configuration
#### 以log举例：
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
                    classes = {RepoLog.class})}, // 定义数据仓库时标记用的空注解，扫描时只过滤对应的仓库
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
### 定义仓库
    package com.example.boot.mongodb.log;

    import com.example.boot.mongodb.config.log.LogBaseRepository;
    import com.example.boot.mongodb.config.log.RepoLog;

    /**
     * Create by bin in 2019/11/5
     **/
    @RepoLog // 标记属于Log数据源
    public interface LogRepository extends LogBaseRepository<DbLog, String> {
    }


### 使用仓库

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
