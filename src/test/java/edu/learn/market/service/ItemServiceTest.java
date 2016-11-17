package edu.learn.market.service;

import edu.learn.market.MarketplaceApp;
import edu.learn.market.domain.Item;
import edu.learn.market.repository.GenericDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MarketplaceApp.class)
public class ItemServiceTest {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Double DEFAULT_START_PRICE = 1D;
    private static final Double UPDATED_START_PRICE = 2D;

    private static final LocalTime DEFAULT_TIME_LEFT = LocalTime.ofNanoOfDay(0L);
    private static final LocalTime UPDATED_TIME_LEFT = LocalTime.now();

    private static final LocalDateTime DEFAULT_START_BIDDING_DATE = LocalDateTime.ofEpochSecond(0L, 0, ZoneOffset.UTC);
    private static final LocalDateTime UPDATED_START_BIDDING_DATE = LocalDateTime.now();

    private static final Boolean DEFAULT_BUY_IT_NOW = false;
    private static final Boolean UPDATED_BUY_IT_NOW = true;

    private static final Double DEFAULT_BID_INCREMENT = 1D;
    private static final Double UPDATED_BID_INCREMENT = 2D;

    @Inject
    private ItemService itemService;

    @Inject
    private GenericDao<Item, Long> itemRepository;

    @Inject
    private EntityManager em;

    private Item item;

    @Before
    public void setUp() throws Exception {
        assertNotNull(itemRepository);
        item = createEntity(em);
    }

    public static Item createEntity(EntityManager em) {
        return new Item()
                .title(DEFAULT_TITLE)
                .description(DEFAULT_DESCRIPTION)
                .startPrice(DEFAULT_START_PRICE)
                .startBiddingDate(DEFAULT_START_BIDDING_DATE)
                .timeLeft(DEFAULT_TIME_LEFT)
                .buyItNow(DEFAULT_BUY_IT_NOW)
                .bidIncrement(DEFAULT_BID_INCREMENT);
    }

    @Test
    @Transactional
    public void save() throws Exception {
        int databaseSizeBeforeCreate = itemRepository.findAll().size();

        // Create
        itemService.save(item);

        // Validate
        itemRepository.flush();
        checkResults(databaseSizeBeforeCreate, itemService.findAll(), DEFAULT_TITLE);
    }

    @Test
    @Transactional
    public void findOne() throws Exception {
        // Initialize the database
        Item one = itemRepository.saveAndFlush(item);

        // Get the item
        one = itemService.findOne(one.getId());
        assertThat(one.getTitle()).isEqualTo(DEFAULT_TITLE);
    }

    @Test
    @Transactional
    public void findAll() throws Exception {
        // Initialize the database
        int databaseSizeBeforeCreate = itemRepository.findAll().size();
        itemRepository.saveAndFlush(item);

        // Validate the Item in the database
        checkResults(databaseSizeBeforeCreate, itemService.findAll(), DEFAULT_TITLE);
    }

    @Test
    @Transactional
    public void getNonExisting() throws Exception {
        // Get the Item
        assertNull(itemService.findOne(Long.MAX_VALUE));
    }

    @Test
    @Transactional
    public void update() throws Exception {
        // Initialize the database
        itemService.save(item);
        int databaseSizeBeforeUpdate = itemRepository.findAll().size();

        // Update the Item
        Item updated = itemRepository.findOne(item.getId());
        updated.title(UPDATED_TITLE);

        itemService.save(updated);

        // Validate the Item in the database
        itemRepository.flush();
        List<Item> items = itemRepository.findAll();
        assertThat(items).hasSize(databaseSizeBeforeUpdate);
        Item test = items.get(items.size() - 1);
        assertThat(test.getTitle()).isEqualTo(UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void delete() throws Exception {
        // Initialize the database
        itemRepository.saveAndFlush(item);
        int databaseSizeBeforeDelete = itemRepository.findAll().size();

        // Get the Item
        itemService.delete(item.getId());

        // Validate the database is empty
        List<Item> items = itemRepository.findAll();
        assertThat(items).hasSize(databaseSizeBeforeDelete - 1);
    }

    private void checkResults(int databaseSizeBeforeUpdate, List<Item> items, String title) {
        assertThat(items).hasSize(databaseSizeBeforeUpdate + 1);
        Item testItem = items.get(items.size() - 1);
        assertThat(testItem.getTitle()).isEqualTo(title);
    }

}