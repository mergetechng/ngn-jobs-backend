//package com.mergetechng.jobs;
//
//import com.mergetechng.jobs.entities.IUser;
//import com.mergetechng.jobs.repositories.UserRepository;
//import com.mergetechng.jobs.services.JobsUserService;
//import lombok.extern.slf4j.Slf4j;
//import org.jetbrains.annotations.NotNull;
//import org.junit.ClassRule;
//import org.junit.jupiter.api.AfterEach;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.boot.test.util.TestPropertyValues;
//import org.springframework.context.ApplicationContextInitializer;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.testcontainers.containers.GenericContainer;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//import java.util.Date;
//import java.util.UUID;
//
//@Testcontainers
//@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
//@ContextConfiguration(initializers = JobUserTest.MongoDbInitializer.class)
//@Slf4j
//public class JobUserTest {
//////    @Container
////    static MongoDB mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
//
//    @ClassRule
//    public static GenericContainer mongo = new GenericContainer("mongo:4-nanoserver").withExposedPorts(27017);
//    private static final Logger LOGGER = LoggerFactory.getLogger(JobsUserService.class);
//
//    @DynamicPropertySource
//    static void setProperties(DynamicPropertyRegistry registry) {
//        mongo.start();
////        String host = genericContainer.getHost();
////        Integer port =  genericContainer.getFirstMappedPort();
////        String connectionString = "mongodb://"+host+":"+port+"/test";
////        MongoDatabaseFactory mongoDatabaseFactory = new SimpleMongoClientDatabaseFactory(connectionString);
////        mongoTemplate =  new MongoTemplate(mongoDatabaseFactory);
//    }
//
//    public static class MongoDbInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//        @Override
//        public void initialize(@NotNull ConfigurableApplicationContext configurableApplicationContext) {
//            LOGGER.info("Overriding Spring Properties for mongodb !!!!!!!!!");
//            TestPropertyValues values = TestPropertyValues.of(
//                    "spring.data.mongodb.host=" + mongo.getContainerIpAddress(),
//                    "spring.data.mongodb.port=" + mongo.getMappedPort(27017),
//                    "spring.data.mongodb.database="+"database_test"
//            );
//            values.applyTo(configurableApplicationContext);
//        }
//    }
//}
