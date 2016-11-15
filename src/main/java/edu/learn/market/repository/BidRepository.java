package edu.learn.market.repository;

import edu.learn.market.domain.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Bid entity.
 */
@SuppressWarnings("unused")
public interface BidRepository extends JpaRepository<Bid, Long> {

}
