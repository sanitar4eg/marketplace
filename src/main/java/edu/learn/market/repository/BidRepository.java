package edu.learn.market.repository;

import edu.learn.market.domain.Bid;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BidRepository {

    Bid save(Bid bid);

    Bid saveAndFlush(Bid bid);

    Bid findOne(Long id);

    List<Bid> findAll();

    void delete(Long id);

}
