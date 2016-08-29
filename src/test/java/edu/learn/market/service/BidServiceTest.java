package edu.learn.market.service;

import edu.learn.market.MarketplaceApp;
import edu.learn.market.domain.Bid;
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

    private Bid bid;

    @Before
    public void setUp() throws Exception {
        bid = createEntity();
    }

    /**
     * Create an entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bid createEntity() {
        return new Bid().bid(DEFAULT_BID);
    }

    @Test
    @Transactional
    public void save() throws Exception {
        int databaseSizeBeforeCreate = bidRepository.findAll().size();

        // Create the Bid
        bidService.save(bid);

        // Validate the Bid in the database
        List<Bid> bids = bidRepository.findAll();
        assertListSize(bids, databaseSizeBeforeCreate + 1);
        checkBidValue(bids.get(bids.size() - 1), DEFAULT_BID);
    }

    @Test
    @Transactional
    public void findOne() throws Exception {
        // Initialize the database
        Bid one = bidRepository.saveAndFlush(bid);

        // Get the bid
        one = bidService.findOne(one.getId());
        checkBidValue(one, DEFAULT_BID);
    }

    @Test
    @Transactional
    public void findAll() throws Exception {
        // Initialize the database
        int databaseSizeBeforeCreate = bidRepository.findAll().size();
        bidRepository.saveAndFlush(bid);

        // Validate the Bid in the database
        List<Bid> bids = bidService.findAll();
        assertListSize(bids, databaseSizeBeforeCreate + 1);
        checkBidValue(bids.get(bids.size() - 1), DEFAULT_BID);
    }

    @Test
    @Transactional
    public void getNonExistingBid() throws Exception {
        // Get the bid
        assertNull(bidService.findOne(Long.MAX_VALUE));
    }

    @Test
    @Transactional
    public void updateBid() throws Exception {
        // Initialize the database
        bidService.save(bid);
        int databaseSizeBeforeUpdate = bidRepository.findAll().size();

        // Update the bid
        Bid updatedBid = bidRepository.findOne(bid.getId());
        updatedBid.bid(UPDATED_BID);

        bidService.save(updatedBid);

        // Validate the Bid in the database
        List<Bid> bids = bidService.findAll();
        assertListSize(bids, databaseSizeBeforeUpdate);
        Bid testBid = bids.get(bids.size() - 1);
        checkBidValue(testBid, UPDATED_BID);
    }

    @Test
    @Transactional
    public void delete() throws Exception {
        // Initialize the database
        bidService.save(bid);
        int databaseSizeBeforeDelete = bidRepository.findAll().size();

        // Get the bid
        bidService.delete(bid.getId());

        // Validate the database is empty
        List<Bid> bids = bidRepository.findAll();
        assertListSize(bids, databaseSizeBeforeDelete - 1);

    }

    private void checkBidValue(Bid testBid, Long bidValue) {
        assertThat(testBid.getBid()).isEqualTo(bidValue);
    }

    private void assertListSize(List<Bid> bids, int expected) {
        assertThat(bids).hasSize(expected);
    }

}