package edu.learn.market.repository;

import edu.learn.market.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Item entity.
 */
@SuppressWarnings("unused")
public interface ItemRepository extends JpaRepository<Item, Long> {

}
