package edu.learn.market.repository.impl;

import edu.learn.market.domain.Bid;
import edu.learn.market.domain.Item;
import edu.learn.market.domain.UserMP;
import edu.learn.market.repository.BidRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class BidJdbcRepositoryImpl implements BidRepository {

    private static final Logger LOG = LoggerFactory.getLogger(BidJdbcRepositoryImpl.class);

    private static final String INSERT_QUERY = "INSERT INTO bids"
            + "(id, bid, item_id, usermp_id) VALUES"
            + "(?,?,?,?)";

    private static final String SELECT_ALL_QUERY = "SELECT "
            + "id, bid, item_id, usermp_id FROM bids";

    private static final String DELETE_QUERY = "DELETE FROM bids "
            + "where id = ?";

    private static final String SELECT_NEXT_SEQUENCE = "select nextval ('hibernate_sequence')";

    private EntityManager entityManager;

    private NamedParameterJdbcTemplate template;

    private DataSource dataSource;

    public BidJdbcRepositoryImpl(EntityManager entityManager, DataSource dataSource) {
        this.entityManager = entityManager;
        template = new NamedParameterJdbcTemplate(dataSource);
        this.dataSource = dataSource;
    }

    @Override
    public Bid save(Bid bid) {
        Assert.notNull(bid);
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try (
                PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)
        ) {
            Long id = bindParameters(bid, statement, connection);
            LOG.debug("Query is ready: " + INSERT_QUERY);
            statement.execute();
            return findOne(id);
        } catch (Exception e) {
            LOG.error("Error when saving Bid", e);
        }
        return null;
    }

    @Override
    public Bid saveAndFlush(Bid bid) {
        return save(bid);
    }

    @Override
    public Bid findOne(Long id) {
        return null;
    }

    @Override
    public List<Bid> findAll() {
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try (
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)
        ) {
            LOG.debug("Query is ready: " + SELECT_ALL_QUERY);
            ResultSet resultSet = statement.executeQuery();
            return extractResults(resultSet);
        } catch (Exception e) {
            LOG.error("Error when saving Bid", e);
        }
        return Collections.emptyList();
    }

    @Override
    public void delete(Long id) {
        Assert.notNull(id);
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try (
                PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)
        ) {
            statement.setLong(1, id);
            LOG.debug("Query is ready: " + DELETE_QUERY);
            statement.execute();
        } catch (Exception e) {
            LOG.error("Error when saving Bid", e);
        }
    }

    private Long getNextId(Connection connection) throws SQLException {
        try (
                PreparedStatement preparedStatement =
                        connection.prepareStatement(SELECT_NEXT_SEQUENCE)) {
            LOG.debug("Query is ready: " + SELECT_NEXT_SEQUENCE);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            LOG.error("Error when getting ID", e);
            throw e;
        }
    }

    private Long bindParameters(Bid bid, PreparedStatement statement, Connection connection) throws SQLException {
        if (bid.getId() == null) {
            bid.id(getNextId(connection));
        }
        statement.setLong(1, bid.getId());
        statement.setLong(2, bid.getBid());
        statement.setLong(3, bid.getItem().getId());
        statement.setLong(4, bid.getUserMP().getId());
        return bid.getId();
    }

    private List<Bid> extractResults(ResultSet resultSet) throws SQLException {
        List<Bid> list = new LinkedList<>();
        while (resultSet.next()) {
            Bid bid = new Bid();
            bid.id(resultSet.getLong(1))
                    .bid(resultSet.getLong(2));
            Optional.ofNullable(resultSet.getLong(3))
                    .ifPresent(itemId -> bid.item(new Item().id(itemId)));
            Optional.ofNullable(resultSet.getLong(4))
                    .ifPresent(userId -> bid.userMP(new UserMP().id(userId)));
            list.add(bid);
        }
        return list;
    }

}
