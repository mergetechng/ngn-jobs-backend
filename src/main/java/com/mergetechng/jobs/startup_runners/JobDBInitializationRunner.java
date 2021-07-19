package com.mergetechng.jobs.startup_runners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mergetechng.jobs.commons.enums.AccountTypeEnum;
import com.mergetechng.jobs.commons.enums.GroupEnum;
import com.mergetechng.jobs.commons.enums.RoleEnum;
import com.mergetechng.jobs.commons.enums.StatusEnum;
import com.mergetechng.jobs.entities.Group1;
import com.mergetechng.jobs.entities.Privilege;
import com.mergetechng.jobs.entities.Role;
import com.mergetechng.jobs.entities.User;
import com.mergetechng.jobs.repositories.GroupRepository;
import com.mergetechng.jobs.repositories.PrivilegeRepository;
import com.mergetechng.jobs.repositories.RoleRepository;
import com.mergetechng.jobs.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class JobDBInitializationRunner implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(JobDBInitializationRunner.class);
    @Autowired
    private PasswordEncoder passwordEncoder;
    private boolean done;
    private static final String NG_JOB_DEFAULT_ADMIN = "ng_jobs_admin";
    public JobDBInitializationRunner() {
        LOGGER.info("Starting the job database initialisation");
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Starting the command line runner...");
        initGroups();
        createDefaultSuperAdmin();
    }

    private void createDefaultSuperAdmin() throws ExecutionException, InterruptedException {
        if (this.userRepository.findByUsername(NG_JOB_DEFAULT_ADMIN) == null ) {
            List<String> groups = List.of(
                    GroupEnum.SUPER_ADMIN_GROUP.name(),
                    GroupEnum.FINANCE_GROUP.name(),
                    GroupEnum.VEHICLE_REGISTRATION_GROUP.name(),
                    GroupEnum.TECHNICAL_GROUP.name(),
                    GroupEnum.VERIFICATION_GROUP.name(),
                    GroupEnum.EMPLOYER_GROUP.name(),
                    GroupEnum.JOB_APPLICANT_GROUP.name()
            );
            List<Group1> group1List = new ArrayList<>();
            User user = new User();
            user.setPassword(passwordEncoder.encode(System.getenv("JOB_ADMIN_PASSWORD")));
            user.setUsername("ng_jobs_admin");
            user.setDateCreated(new Date());
            user.setAccountNonLocked(true);
            user.setEnabled(true);
            user.setCredentialNonExpired(true);
            user.setDateModified(new Date());
            user.setUserId("ng_jobs_admin");
            user.setFirstName("ng_jobs");
            user.setLastName("ng_jobs");
            user.setStatus(StatusEnum.ACTIVE.name());
            user.setEmailVerified(true);
            user.setEmail("developer@ngjobs.com");
            user.setPhone("+2348160110719"); //can be updated later
            user.setRegistrationMode("auto");
            user.setAccountType(AccountTypeEnum.ADMIN.name());
            user.setAccountNonExpired(true);
            groups.forEach(s -> {
                if (groupRepository.findByGroupId(s).isPresent()) {
                    group1List.add(groupRepository.findByGroupId(s).get());
                } else {
                    LOGGER.info("Skipping " + s + " Group Admin with the ID not added");
                }
            });
            user.setGroupId(group1List);
            userRepository.insert(user);
        }
    }

    private void initGroups() throws JsonProcessingException {
        List<String> actions = List.of("ADD", "UPDATE", "DELETE", "GET");
        LOGGER.info("Number of user : {} " , userRepository.count());
        LOGGER.info("Number of group : {} " , groupRepository.count());
        LOGGER.info("Number of role : {} " , roleRepository.count());
        LOGGER.info("Number of privilege : {} " , privilegeRepository.count());

        if (this.groupRepository.findByGroupId(GroupEnum.SUPER_ADMIN_GROUP.name()).isEmpty()) {
            List<List<String>> superAdminRoleGroup = List.of(
                    List.of(RoleEnum.ADMIN_DASHBOARD.name(), RoleEnum.ADMIN_DASHBOARD.description),
                    List.of(RoleEnum.USER_MANAGEMENT.name(), RoleEnum.USER_MANAGEMENT.description),
                    List.of(RoleEnum.PUBLIC_API.name(), RoleEnum.PUBLIC_API.description),
                    List.of(RoleEnum.SERVER_MANAGEMENT.name(), RoleEnum.SERVER_MANAGEMENT.description),
                    List.of(RoleEnum.ROLES_MANAGEMENT.name(), RoleEnum.ROLES_MANAGEMENT.description),
                    List.of(RoleEnum.GROUPS_MANAGEMENT.name(), RoleEnum.GROUPS_MANAGEMENT.description),
                    List.of(RoleEnum.PRIVILEGE_MANAGEMENT.name(), RoleEnum.PRIVILEGE_MANAGEMENT.description),
                    List.of(RoleEnum.ADMIN_MANAGEMENT.name(), RoleEnum.ADMIN_MANAGEMENT.description),
                    List.of(RoleEnum.RECEIPT_MANAGEMENT.name(), RoleEnum.RECEIPT_MANAGEMENT.description),
                    List.of(RoleEnum.INVOICE_MANAGEMENT.name(), RoleEnum.INVOICE_MANAGEMENT.description),
                    List.of(RoleEnum.SERVER_MONITORING.name(), RoleEnum.SERVER_MONITORING.description),
                    List.of(RoleEnum.PAYMENT_MANAGEMENT.name(), RoleEnum.PAYMENT_MANAGEMENT.description)
            );
            initiateGroupRolesPermissions(GroupEnum.SUPER_ADMIN_GROUP.name(), superAdminRoleGroup, actions);
        }

        if (this.groupRepository.findByGroupId(GroupEnum.ADMIN_USER_GROUP.name()).isEmpty()) {
            List<List<String>> adminRoleGroup = List.of(
                    List.of(RoleEnum.ADMIN_DASHBOARD.name(), RoleEnum.ADMIN_DASHBOARD.description),
                    List.of(RoleEnum.PAYMENT_MANAGEMENT.name(), RoleEnum.PAYMENT_MANAGEMENT.description),
                    List.of(RoleEnum.USER_MANAGEMENT.name(), RoleEnum.USER_MANAGEMENT.description),
                    List.of(RoleEnum.PUBLIC_API.name(), RoleEnum.PUBLIC_API.description),
                    List.of(RoleEnum.RECEIPT_MANAGEMENT.name(), RoleEnum.RECEIPT_MANAGEMENT.description)
            );
            initiateGroupRolesPermissions(GroupEnum.ADMIN_USER_GROUP.name(), adminRoleGroup, actions);
        }

        if (this.groupRepository.findByGroupId(GroupEnum.FINANCE_GROUP.name()).isEmpty()) {
            List<List<String>> adminFinanceRoleGroup = List.of(
                    List.of(RoleEnum.PAYMENT_MANAGEMENT.name(), RoleEnum.PAYMENT_MANAGEMENT.description),
                    List.of(RoleEnum.WALLET_MANAGEMENT.name(), RoleEnum.WALLET_MANAGEMENT.description)
            );
            initiateGroupRolesPermissions(GroupEnum.FINANCE_GROUP.name(), adminFinanceRoleGroup, actions);
        }

        if (this.groupRepository.findByGroupId(GroupEnum.VEHICLE_REGISTRATION_GROUP.name()).isEmpty()) {
            List<List<String>> adminFinanceRoleGroup = List.of(
                    List.of(RoleEnum.PAYMENT_MANAGEMENT.name(), RoleEnum.PAYMENT_MANAGEMENT.description),
                    List.of(RoleEnum.WALLET_MANAGEMENT.name(), RoleEnum.WALLET_MANAGEMENT.description)
            );
            initiateGroupRolesPermissions(GroupEnum.VEHICLE_REGISTRATION_GROUP.name(), adminFinanceRoleGroup, actions);
        }

        if (this.groupRepository.findByGroupId(GroupEnum.VERIFICATION_GROUP.name()).isEmpty()) {
            List<List<String>> adminVerificationRoleGroup = List.of(
                    List.of(RoleEnum.ACCOUNT_VERIFICATION.name(), RoleEnum.ACCOUNT_VERIFICATION.description),
                    List.of(RoleEnum.VEHICLE_VERIFICATION.name(), RoleEnum.VEHICLE_VERIFICATION.description),
                    List.of(RoleEnum.ADDRESS_VERIFICATION.name(), RoleEnum.ADDRESS_VERIFICATION.description)
            );
            initiateGroupRolesPermissions(GroupEnum.VERIFICATION_GROUP.name(), adminVerificationRoleGroup, actions);
        }

        if (this.groupRepository.findByGroupId(GroupEnum.TECHNICAL_GROUP.name()).isEmpty()) {
            List<List<String>> adminTechnicalRoleGroup = List.of(
                    List.of(RoleEnum.API_MANAGEMENT.name(), RoleEnum.API_MANAGEMENT.description),
                    List.of(RoleEnum.SERVER_MANAGEMENT.name(), RoleEnum.SERVER_MANAGEMENT.description),
                    List.of(RoleEnum.LOG_REQUEST_AUDIT.name(), RoleEnum.LOG_REQUEST_AUDIT.description)
            );
            initiateGroupRolesPermissions(GroupEnum.TECHNICAL_GROUP.name(), adminTechnicalRoleGroup, actions);
        }

        if (this.groupRepository.findByGroupId(GroupEnum.EMPLOYER_GROUP.name()).isEmpty()) {
            List<List<String>> riderRoleGroup = List.of(
                    List.of(RoleEnum.JOB_EMPLOYER_USER.name(), RoleEnum.JOB_EMPLOYER_USER.description)
            );
            initiateGroupRolesPermissions(GroupEnum.EMPLOYER_GROUP.name(), riderRoleGroup, actions);
        }

        if (this.groupRepository.findByGroupId(GroupEnum.JOB_APPLICANT_GROUP.name()).isEmpty()) {
            List<List<String>> driverRoleGroup = List.of(
                    List.of(RoleEnum.JOB_APPLICANT_USER.name(), RoleEnum.JOB_APPLICANT_USER.description)
            );
            initiateGroupRolesPermissions(GroupEnum.JOB_APPLICANT_GROUP.name(), driverRoleGroup, actions);
        }
    }

    /**
     * @param roles     The roles that will be assigned to the group
     * @param groupName The name of the group
     * @param actions   The list of actions that will be used to create the permissions for the roles
     */
    private void initiateGroupRolesPermissions(String groupName, List<List<String>> roles, List<String> actions) {

        Group1 adminGroup = new Group1();
        adminGroup.setGroupName(groupName);
        adminGroup.setDateCreated(new Date());
        adminGroup.setGroupId(groupName);
        adminGroup.setDateCreated(new Date());
        adminGroup.setCreatedBy("System");

        Map<String, List<String>> groupHasRoles = new HashMap<>();
        List<Role> roleList = new ArrayList<>();

        roles.forEach(s -> {
            Role role = new Role(s.get(0));
            role.setDescription(s.get(1));
            role.setName(s.get(0));
            role.setRoleId(s.get(0));
            role.setDateCreated(new Date());

            List<Privilege> privilegeList = new ArrayList<>(List.of());

            AtomicReference<String> _name = new AtomicReference<>("");
            actions.forEach(action -> {
                _name.set(s.get(0) + "_" + action);
                Privilege privilege = new Privilege();
                privilege.setCreatedBy("System");
                privilege.setPrivilegeId(_name.get());
                privilege.setName(_name.get());
                privilege.setDescription("Role " + s.get(0) + " " + action + " privilege");
                privilege.setDateCreated(new Date());
                privilegeList.add(privilege);
                this.privilegeRepository.insert(privilege);
                LOGGER.info("Currently adding this :  {}", _name.get());
            });
            role.setPrivilegeId(privilegeList);
            roleRepository.insert(role);
            LOGGER.info("Currently adding this :  {}", role.getRoleId());
            roleList.add(role);
        });
        adminGroup.setRoleId(roleList);
        groupRepository.insert(adminGroup);
        LOGGER.info("Created "+adminGroup.getGroupId() + " Group");

    }
}