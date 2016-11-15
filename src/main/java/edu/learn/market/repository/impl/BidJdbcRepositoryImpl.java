package edu.learn.market.repository.impl;

import edu.learn.market.domain.Bid;
import edu.learn.market.repository.BidRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;

@Repository
public class BidJdbcRepositoryImpl implements BidRepository {

    private static final Logger LOG = LoggerFactory.getLogger(BidJdbcRepositoryImpl.class);

    private static final String INSERT_QUERY = "INSERT INTO bids"
            + "(id, bid, item_id, usermp_id) VALUES"
            + "(?,?,?,?)";

    EntityManager entityManager;

    NamedParameterJdbcTemplate template;

    DataSource dataSource;

    public BidJdbcRepositoryImpl(EntityManager entityManager, DataSource dataSource) {
        this.entityManager = entityManager;
        template = new NamedParameterJdbcTemplate(dataSource);
        this.dataSource = dataSource;
    }

    @Override
    public Bid save(Bid bid) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)
        ) {
            statement.setLong(1, getNextId());
            statement.setLong(2, bid.getBid());
            statement.setLong(3, bid.getItem().getId());
            statement.setLong(4, bid.getUserMP().getId());
            statement.execute();
        } catch (Exception e) {
            LOG.error("Error when saving Bid", e);
        }
        return null;
    }

    @Override
    public Bid saveAndFlush(Bid bid) {
        return null;
    }

    @Override
    public Bid findOne(Long id) {
        return null;
    }

    @Override
    public List<Bid> findAll() {
        return Collections.emptyList();
    }

    @Override
    public void delete(Long id) {

    }

    private Long getNextId() {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement("select nextval ('hibernate_sequence')")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (Exception e) {
            LOG.error("Error when getting ID", e);
            return null;
        }
    }
}
