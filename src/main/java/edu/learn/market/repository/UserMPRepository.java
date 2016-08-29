package edu.learn.market.repository;

import edu.learn.market.domain.UserMP;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the UserMP entity.
 */
@SuppressWarnings("unused")
public interface UserMPRepository extends JpaRepository<UserMP, Long> {

}

