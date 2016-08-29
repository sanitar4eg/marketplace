package edu.learn.market.service;

import edu.learn.market.domain.UserMP;

import java.util.List;

/**
 * Service Interface for managing UserMP.
 */
public interface UserMPService {

    /**
     * Save a userMP.
     *
     * @param userMP the entity to save
     * @return the persisted entity
     */
    UserMP save(UserMP userMP);

    /**
     * Get all the userMPS.
     *
     * @return the list of entities
     */
    List<UserMP> findAll();

    /**
     * Get the "id" userMP.
     *
     * @param id the id of the entity
     * @return the entity
     */
    UserMP findOne(Long id);

    /**
     * Delete the "id" userMP.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
