package edu.learn.market.repository.impl;

import edu.learn.market.domain.Bid;
import edu.learn.market.domain.Item;
import edu.learn.market.domain.UserMP;
import edu.learn.market.repository.BidRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

@Repository
@SuppressWarnings("unused")
public class BidJdbcRepositoryImpl implements BidRepository {

    private static final Logger LOG = LoggerFactory.getLogger(BidJdbcRepositoryImpl.class);

    private static final String UPSERT_QUERY = "INSERT INTO bids"
            + "(id, bid, item_id, usermp_id) VALUES"
            + "(:id, :bid, :item_id, :usermp_id) ON CONFLICT (id) DO "
            + "UPDATE SET bid = :bid, item_id = :item_id, usermp_id = :usermp_id";

    private static final String SELECT_ALL_QUERY = "SELECT "
            + "id, bid, item_id, usermp_id FROM bids";

    private static final String SELECT_ONE_QUERY = "SELECT "
            + "id, bid, item_id, usermp_id FROM bids "
            + "WHERE id = ?";

    private static final String DELETE_QUERY = "DELETE FROM bids "
            + "where id = ?";

    private static final String SELECT_NEXT_SEQUENCE = "SELECT nextval ('hibernate_sequence')";

    private NamedParameterJdbcTemplate namedTemplate;

    private JdbcTemplate template;

    private BidsResultSetExtractor setExtractor;

    public BidJdbcRepositoryImpl(DataSource dataSource) {
        this.namedTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.template = new JdbcTemplate(dataSource);
        this.setExtractor = new BidsResultSetExtractor();
    }

    private class BidsResultSetExtractor implements ResultSetExtractor<List<Bid>> {

        @Override
        public List<Bid> extractData(ResultSet rs) throws SQLException, DataAccessException {
            List<Bid> list = new LinkedList<>();
            while (rs.next()) {
                Bid bid = new Bid();
                bid.id(rs.getLong(1))
                        .bid(rs.getLong(2));
                Optional.ofNullable(rs.getLong(3))
                        .ifPresent(itemId -> bid.item(new Item().id(itemId)));
                Optional.ofNullable(rs.getLong(4))
                        .ifPresent(userId -> bid.userMP(new UserMP().id(userId)));
                list.add(bid);
            }
            return list;
        }
    }

    @Override
    public Bid save(Bid bid) {
        Assert.notNull(bid);
        LOG.debug("Query is ready: " + UPSERT_QUERY);
        namedTemplate.update(UPSERT_QUERY, getParamMap(bid));
        return bid;
    }

    @Override
    public Bid saveAndFlush(Bid bid) {
        return save(bid);
    }

    @Override
    public Bid findOne(Long id) {
        Assert.notNull(id);
        LOG.debug("Query is ready: " + SELECT_ONE_QUERY);
        List<Bid> list = template.query(SELECT_ONE_QUERY, new Long[]{id}, setExtractor);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Bid> findAll() {
        LOG.debug("Query is ready: " + SELECT_ALL_QUERY);
        return template.query(SELECT_ALL_QUERY, setExtractor);
    }

    @Override
    public void delete(Long id) {
        Assert.notNull(id);
        LOG.debug("Query is ready: " + DELETE_QUERY);
        template.update(DELETE_QUERY, id);
    }

    private Long getNextId() {
        LOG.debug("Query is ready: " + SELECT_NEXT_SEQUENCE);
        return template.queryForObject(SELECT_NEXT_SEQUENCE, Long.class);
    }

    private Map<String, ?> getParamMap(Bid bid) {
        if (bid.getId() == null) {
            bid.id(getNextId());
        }
        Map<String, SqlParameterValue> map = new HashMap<>();
        map.put("id", new SqlParameterValue(Types.LONGVARBINARY, bid.getId()));
        map.put("bid", new SqlParameterValue(Types.LONGVARBINARY, bid.getBid()));
        map.put("item_id",
                new SqlParameterValue(Types.LONGVARBINARY, Optional.ofNullable(bid.getItem())
                        .map(Item::getId)
                        .orElse(null)));
        map.put("usermp_id",
                new SqlParameterValue(Types.LONGVARBINARY, Optional.ofNullable(bid.getUserMP())
                        .map(UserMP::getId)
                        .orElse(null)));
        return map;
    }
}
