package edu.learn.market.service;

import edu.learn.market.domain.Bid;

import java.util.List;

/**
 * Service Interface for managing Bid.
 */
public interface BidService {

    /**
     * Save a bid.
     *
     * @param bid the entity to save
     * @return the persisted entity
     */
    Bid save(Bid bid);

    /**
     * Get all the bids.
     *
     * @return the list of entities
     */
    List<Bid> findAll();

    /**
     * Get the "id" bid.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Bid findOne(Long id);

    /**
     * Delete the "id" bid.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
