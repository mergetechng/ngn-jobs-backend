//package com.mergetechng.jobs;
//
//import com.mergetechng.jobs.entities.User;
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
//    @Autowired
//    private UserRepository userRepository;
//
//    @AfterEach
//    void cleanUp() {
//        this.userRepository.deleteAll();
//    }
//
//    @Test
//    void createUser() {
//
//        String user1Id = UUID.randomUUID().toString();
//        String user2Id = UUID.randomUUID().toString();
//        String user3Id = UUID.randomUUID().toString();
//
//        User user = new User(user1Id);
//        user.setUsername("test");
//        user.setPassword("testPassword");
//        user.setEnabled(false);
//        user.setEmail("test@gmail.com");
//        user.setEmailVerified(false);
//        user.setDateModified(new Date());
//        user.setDateCreated(new Date());
//        user.setModifiedBy("System test");
//        this.userRepository.save(user);
//
//
//        User user2 = new User(user2Id);
//        user2.setUsername("test");
//        user2.setPassword("testPassword");
//        user2.setEnabled(false);
//        user2.setEmail("test@gmail.com");
//        user2.setEmailVerified(false);
//        this.userRepository.save(user2);
//
//
//        User user3 = new User(user3Id);
//        user3.setUsername("test3");
//        user3.setPassword("testPassword3");
//        user3.setEnabled(true);
//        user3.setEmail("test3@gmail.com");
//        user3.setEmailVerified(true);
//
//        this.userRepository.save(user3);
//        this.userRepository.save(user2);
//        this.userRepository.save(user3);
//
//        assertNull(user.getAccountType());
//
//        assertEquals(user.getUserId(), user1Id);
//        assertEquals(user2.getUserId(), user1Id);
//
//        assertNotEquals(user3.getUserId(), user1Id);
//
//        //count the number of users in the database
//        assertNotEquals(userRepository.findAll().isEmpty(), false);
//        assertNotEquals(userRepository.findAll().listIterator().hasNext(), false);
//
//        //check the size of the user
//        assertNotEquals(userRepository.findAll().size(), 4);
//
//
//        // check if present and the Id not Equals to null
//        assertNotEquals(userRepository.findById(user1Id), null);
//        assertNotEquals(userRepository.findById(user1Id).isPresent(), false);
//
//        //verify the userId
//        assertNotEquals(userRepository.findById(user1Id).get().getUserId(), user3Id);
//        assertEquals(userRepository.findById(user1Id).get().getUserId(), user1Id);
//
//        // check if present and the Id not Equals to null
//        assertNotEquals(userRepository.findById(user2Id), null);
//        assertNotEquals(userRepository.findById(user2Id).isPresent(), false);
//
//        //verify the userId
//        assertNotEquals(userRepository.findById(user2Id).get().getUserId(), user3Id);
//        assertEquals(userRepository.findById(user2Id).get().getUserId(), user1Id);
//
//
//        // check if present and the Id not Equals to null
//        assertNotEquals(userRepository.findById(user3Id), null);
//        assertNotEquals(userRepository.findById(user3Id).isPresent(), false);
//
//        //verify the userId
//        assertNotEquals(userRepository.findById(user3Id).get().getUserId(), user2Id);
//        assertEquals(userRepository.findById(user3Id).get().getUserId(), user1Id);
//
//        //check if the users has next items
//        assertNotEquals(userRepository.findAll().listIterator().hasNext(), false);
//        assertNotEquals(userRepository.findAll().listIterator().hasNext(), false);
//
//    }
//
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
