package edu.learn.market.repository;

import edu.learn.market.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@SuppressWarnings("unused")
@NoRepositoryBean
public interface ItemRepository extends JpaRepository<Item, Long> {

}
