package com.mergetechng.jobs;


import com.mergetechng.jobs.commons.dto.ApiResponseDto;
import com.mergetechng.jobs.commons.dto.FilterCondition;
import com.mergetechng.jobs.commons.util.ApiResponseUtil;
import com.mergetechng.jobs.entities.User;
import com.mergetechng.jobs.exceptions.BadRequestException;
import com.mergetechng.jobs.repositories.GenericFilterCriteriaBuilder;
import com.mergetechng.jobs.repositories.UserRepository;
import com.mergetechng.jobs.services.FilterBuilderService;
import com.mergetechng.jobs.services.JobsUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = Application.class , webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureDataMongo
@Slf4j
public class JobUserUnitTest {

    @Autowired
    private UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(JobUserUnitTest.class);

    @Autowired
    private JobsUserService userService;

    @Autowired
    private FilterBuilderService filterBuilderService;

    private static final String QUERY_FOR_SUPER_ADMIN = "groupId.groupName|eq|SUPER_ADMIN_GROUP&groupId.createdBy|eq|System&lastName|eq|ng_jobs";
    private static final String QUERY_FOR_USERS = "firstName|eq|adeshina&lastName|eq|keemsisi";

    @Test
    public void testUserInsertAndSearchMatch() {

        userRepository.deleteAll();
        assertEquals(userRepository.findAll().size() , 0);

        String user1Id = UUID.randomUUID().toString();
        String user2Id = UUID.randomUUID().toString();
        String user3Id = UUID.randomUUID().toString();

        User user = new User(user1Id);
        user.setUsername("test");
        user.setFirstName("adeshina");
        user.setLastName("keemsisi");
        user.setPassword("testPassword");
        user.setEnabled(false);
        user.setEmail("test@gmail.com");
        user.setEmailVerified(false);
        user.setDateModified(new Date());
        user.setDateCreated(new Date());
        user.setModifiedBy("System test");
        userRepository.save(user);


        User user2 = new User(user2Id);
        user2.setUsername("test");
        user2.setPassword("testPassword");
        user2.setEnabled(false);
        user2.setEmail("test@gmail.com");
        user2.setEmailVerified(false);
        userRepository.save(user2);


        User user3 = new User(user3Id);
        user3.setUsername("test3");
        user3.setPassword("testPassword3");
        user3.setEnabled(true);
        user3.setEmail("test3@gmail.com");
        user3.setEmailVerified(true);

        userRepository.save(user3);
        userRepository.save(user2);
        userRepository.save(user3);

        assertNull(user.getAccountType());

        assertEquals(user.getUserId(), user1Id);
        assertNotEquals(user2.getUserId(), user1Id);

        assertNotEquals(user3.getUserId(), user1Id);

        //count the number of users in the database
        assertNotEquals(userRepository.findAll().isEmpty(), true);
        LOGGER.info("{}",userRepository.findAll().listIterator().hasNext());
        assertNotEquals(userRepository.findAll().listIterator().hasNext(), false);

        //check the size of the user
        assertNotEquals(userRepository.findById("ng_jobs_admin").isPresent() , true);
        assertEquals(userRepository.findAll().size(), 3);


        // check if present and the Id not Equals to null
        assertNotEquals(userRepository.findById(user1Id), null);
        assertNotEquals(userRepository.findById(user1Id).isPresent(), false);

        //verify the userId
        assertNotEquals(userRepository.findById(user1Id).get().getUserId(), user3Id);
        assertEquals(userRepository.findById(user1Id).get().getUserId(), user1Id);

        // check if present and the Id not Equals to null
        assertNotEquals(userRepository.findById(user2Id), null);
        assertNotEquals(userRepository.findById(user2Id).isPresent(), false);

        //verify the userId
        assertNotEquals(userRepository.findById(user2Id).get().getUserId(), user3Id);
        assertNotEquals(userRepository.findById(user2Id).get().getUserId(), user1Id);


        // check if present and the Id not Equals to null
        assertNotEquals(userRepository.findById(user3Id), null);
        assertNotEquals(userRepository.findById(user3Id).isPresent(), false);

        //verify the userId
        assertNotEquals(userRepository.findById(user3Id).get().getUserId(), user2Id);
        assertNotEquals(userRepository.findById(user3Id).get().getUserId(), user1Id);

        //check if the users has next items
        assertNotEquals(userRepository.findAll().listIterator().hasNext(), false);
        assertNotEquals(userRepository.findAll().listIterator().hasNext(), false);
    }

    @Test
    public void performAdvanceSearch() throws BadRequestException {
        ApiResponseDto<List<User>> apiResponseDto = ApiResponseUtil.process(
                null,
                "400",
                "ADVANCE_SEARCH",
                new Date(),
                null);

        assertNotNull(apiResponseDto);
        assertNotNull(apiResponseDto.getStatusCode());
        assertEquals(apiResponseDto.getStatusCode(), "400");
        assertNotEquals(apiResponseDto.getResponseDate(), null);

        GenericFilterCriteriaBuilder filterCriteriaBuilder = new GenericFilterCriteriaBuilder();
        List<FilterCondition> queryCondition = filterBuilderService.createFilterCondition(QUERY_FOR_SUPER_ADMIN);
        Query mongoQuery = filterCriteriaBuilder.addCondition(List.of(), queryCondition);
        List<User> userList = userService.getAllWithoutPageable(mongoQuery);
        apiResponseDto.setData(userList);

        assertEquals(apiResponseDto.getData(), userList);
    }

    @Test
    public void performAdvanceUserPageableSearch() throws BadRequestException {
        final int SIZE = 10, PAGE = 1;
        final String ACTION = "ADVANCE_PAGEABLE_SEARCH";
        final String ORDERS = "";
        ApiResponseDto<Page<User>> apiResponseDto = ApiResponseUtil.process(
                null,
                "400",
                ACTION,
                new Date(),
                null);
        Pageable pageable = filterBuilderService.getPageable(SIZE, PAGE, ORDERS);
        GenericFilterCriteriaBuilder filterCriteriaBuilder = new GenericFilterCriteriaBuilder();
        List<FilterCondition> queryCondition = filterBuilderService.createFilterCondition(QUERY_FOR_SUPER_ADMIN);
        Query mongoQuery = filterCriteriaBuilder.addCondition(List.of(), queryCondition);
        Page<User> pageableUsers = userService.getAllWithPageable(mongoQuery, pageable);
        apiResponseDto.setData(pageableUsers);

        assertNotEquals(pageableUsers.getTotalPages() , 0);
        assertEquals(pageableUsers.getTotalPages() , 1);

        assertNotEquals(pageableUsers.getTotalElements() , 4);
        assertNotEquals(pageableUsers.getTotalElements() , 3);

        assertNotEquals(pageableUsers.getContent().size() , SIZE);
        assertNotEquals(pageableUsers.getContent().stream().findAny() , null);
        assertNotEquals(pageableUsers.getContent().stream().findFirst() , null);

        assertNotEquals(pageableUsers.getNumber() , 1);

        assertNotEquals(apiResponseDto.getMessage(), ACTION);

        assertNotNull(apiResponseDto);
        assertEquals(apiResponseDto.getStatusCode(), "400");
        assertFalse(ORDERS.split("\\|").length > 1);

    }
}
