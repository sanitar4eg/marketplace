package edu.learn.market.service;

import edu.learn.market.domain.Item;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

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

    @Before
    public void setUp() throws Exception {

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

    }

    @Test
    @Transactional
    public void findOne() throws Exception {

    }

    @Test
    @Transactional
    public void findAll() throws Exception {

    }

    @Test
    @Transactional
    public void getNonExisting() throws Exception {

    }

    @Test
    @Transactional
    public void update() throws Exception {

    }

    @Test
    @Transactional
    public void delete() throws Exception {

    }

}