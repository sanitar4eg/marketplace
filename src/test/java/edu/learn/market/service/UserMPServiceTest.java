package edu.learn.market.service;

import edu.learn.market.MarketplaceApp;
import edu.learn.market.domain.UserMP;
import edu.learn.market.repository.UserMPRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MarketplaceApp.class)
public class UserMPServiceTest {

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BILLING_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_BILLING_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAA@AAAAA";
    private static final String UPDATED_EMAIL = "BBBBB@BBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    @Inject
    private UserMPService userMPService;

    @Inject
    private UserMPRepository userMPRepository;

    @Inject
    private EntityManager em;

    private UserMP userMP;

    @Before
    public void setUp() throws Exception {
        userMP = createEntity(em);
    }

    public static UserMP createEntity(EntityManager em) {
        return new UserMP()
                .fullName(DEFAULT_FULL_NAME)
                .billingAddress(DEFAULT_BILLING_ADDRESS)
                .email(DEFAULT_EMAIL)
                .password(DEFAULT_PASSWORD);
    }

    @Test
    @Transactional
    public void save() throws Exception {
        int databaseSizeBeforeCreate = userMPRepository.findAll().size();

        // Create the UserMP
        userMPService.save(userMP);

        // Validate the UserMP in the database
        checkResults(databaseSizeBeforeCreate, userMPRepository.findAll(), DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    public void findOne() throws Exception {
        // Initialize the database
        UserMP one = userMPRepository.saveAndFlush(userMP);

        // Get the UserMP
        one = userMPService.findOne(one.getId());

        // Validate
        assertThat(one.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    public void findAll() throws Exception {
        // Initialize the database
        int databaseSizeBeforeCreate = userMPRepository.findAll().size();
        userMPRepository.saveAndFlush(userMP);

        // Validate the UserMP in the database
        checkResults(databaseSizeBeforeCreate, userMPService.findAll(), DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    public void getNonExisting() throws Exception {
        assertNull(userMPService.findOne(Long.MAX_VALUE));
    }

    @Test
    @Transactional
    public void update() throws Exception {
        // Initialize the database
        userMPRepository.save(userMP);
        int databaseSizeBeforeUpdate = userMPRepository.findAll().size();

        // Update
        UserMP one = userMPRepository.findOne(userMP.getId());
        one.email(UPDATED_EMAIL);

        userMPService.save(one);

        // Validate
        List<UserMP> users = userMPRepository.findAll();
        assertThat(users).hasSize(databaseSizeBeforeUpdate);
        UserMP testUserMP = users.get(users.size() - 1);
        assertThat(testUserMP.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void delete() throws Exception {
        // Initialize the database
        userMPRepository.save(userMP);
        int databaseSizeBeforeDelete = userMPRepository.findAll().size();

        // Get the UserMP
        userMPService.delete(userMP.getId());

        // Validate the database is empty
        List<UserMP> users = userMPRepository.findAll();
        assertThat(users).hasSize(databaseSizeBeforeDelete - 1);

    }

    private void checkResults(int databaseSizeBefore, List<UserMP> users, String email) {
        assertThat(users).hasSize(databaseSizeBefore + 1);
        UserMP testUserMP = users.get(users.size() - 1);
        assertThat(testUserMP.getEmail()).isEqualTo(email);
    }
}