package edu.learn.market.repository;

import edu.learn.market.domain.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Bid entity.
 */
@SuppressWarnings("unused")
public interface BidRepository extends JpaRepository<Bid, Long> {

    @Query("select distinct bid from Bid bid left join fetch bid.users left join fetch bid.items")
    List<Bid> findAllWithEagerRelationships();

    @Query("select bid from Bid bid left join fetch bid.users left join fetch bid.items where bid.id =:id")
    Bid findOneWithEagerRelationships(@Param("id") Long id);

}
