package edu.learn.market.service;

import edu.learn.market.MarketplaceApp;
import edu.learn.market.domain.Bid;
import edu.learn.market.domain.Item;
import edu.learn.market.domain.UserMP;
import edu.learn.market.repository.BidRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

/**
 * Test class for the BidResource REST controller.
 *
 * @see BidService
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MarketplaceApp.class)
public class BidServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(BidServiceTest.class);

    private static final Long DEFAULT_BID = 1L;
    private static final Long UPDATED_BID = 2L;

    @Inject
    private BidService bidService;

    @Inject
    private BidRepository bidRepository;

    @Inject
    private EntityManager em;

    private Bid bid;

    @Before
    public void setUp() throws Exception {
        bid = createEntity(em);
    }

    /**
     * Create an entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bid createEntity(EntityManager em) {
        Bid bid = new Bid().bid(DEFAULT_BID);
        // Add required entity
        UserMP userMP = UserMPServiceTest.createEntity(em);
        em.persist(userMP);
        em.flush();
        bid.setUserMP(userMP);
        // Add required entity
        Item item = ItemServiceTest.createEntity(em);
        em.persist(item);
        em.flush();
        bid.setItem(item);
        return bid;
    }

    @Test
    @Transactional
    public void save() throws Exception {
        int databaseSizeBeforeCreate = bidRepository.findAll().size();

        // Create the Bid
        bidService.save(bid);

        // Validate the Bid in the database
        checkResults(databaseSizeBeforeCreate, bidService.findAll(), DEFAULT_BID);
    }

    @Test
    @Transactional
    public void findOne() throws Exception {
        // Initialize the database
        Bid one = bidRepository.saveAndFlush(bid);

        // Get the bid
        one = bidService.findOne(one.getId());
        assertThat(one.getBid()).isEqualTo(DEFAULT_BID);
    }

    @Test
    @Transactional
    public void findAll() throws Exception {
        // Initialize the database
        int databaseSizeBeforeCreate = bidRepository.findAll().size();
        bidRepository.saveAndFlush(bid);

        // Validate the Bid in the database
        checkResults(databaseSizeBeforeCreate, bidService.findAll(), DEFAULT_BID);
    }

    @Test
    @Transactional
    public void getNonExistingBid() throws Exception {
        // Get the bid
        assertNull(bidService.findOne(Long.MAX_VALUE));
    }

    @Test
    @Transactional
    public void update() throws Exception {
        // Initialize the database
        bidService.save(bid);
        int databaseSizeBeforeUpdate = bidRepository.findAll().size();

        // Update the bid
        Bid updatedBid = bidRepository.findOne(bid.getId());
        updatedBid.bid(UPDATED_BID);

        bidService.save(updatedBid);

        // Validate the Bid in the database
        List<Bid> bids = bidRepository.findAll();
        assertThat(bids).hasSize(databaseSizeBeforeUpdate);
        Bid testBid = bids.get(bids.size() - 1);
        assertThat(testBid.getBid()).isEqualTo(UPDATED_BID);
    }

    @Test
    @Transactional
    public void delete() throws Exception {
        // Initialize the database
        bidRepository.save(bid);
        int databaseSizeBeforeDelete = bidRepository.findAll().size();

        // Get the bid
        bidService.delete(bid.getId());

        // Validate the database is empty
        List<Bid> bids = bidRepository.findAll();
        assertThat(bids).hasSize(databaseSizeBeforeDelete - 1);
    }

    private void checkResults(int databaseSizeBeforeUpdate, List<Bid> bids, Long bid) {
        assertThat(bids).hasSize(databaseSizeBeforeUpdate + 1);
        Bid testBid = bids.get(bids.size() - 1);
        assertThat(testBid.getBid()).isEqualTo(bid);
    }
}