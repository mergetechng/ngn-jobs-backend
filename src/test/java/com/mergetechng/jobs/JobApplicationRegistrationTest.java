package com.mergetechng.jobs;

import com.mergetechng.jobs.repositories.GroupRepository;
import com.mergetechng.jobs.repositories.PrivilegeRepository;
import com.mergetechng.jobs.repositories.RoleRepository;
import com.mergetechng.jobs.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(properties = {"spring.data.mongodb.database=test_db"})
@ContextConfiguration(classes = {JobApplicationRegistrationTest.class})
public class JobApplicationRegistrationTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void create_new_admin_user_test(){

    }
}
