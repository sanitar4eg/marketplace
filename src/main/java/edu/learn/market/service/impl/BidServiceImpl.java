package edu.learn.market.service.impl;

import edu.learn.market.domain.Bid;
import edu.learn.market.repository.BidRepository;
import edu.learn.market.service.BidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Bid.
 */
@Service
@Transactional
public class BidServiceImpl implements BidService {

    private static final Logger LOG = LoggerFactory.getLogger(BidServiceImpl.class);

    @Inject
    private BidRepository bidRepository;

    /**
     * Save a bid.
     *
     * @param bid the entity to save
     * @return the persisted entity
     */
    public Bid save(Bid bid) {
        LOG.debug("Request to save Bid : {}", bid);
        return bidRepository.save(bid);
    }

    /**
     * Get all the bids.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Bid> findAll() {
        LOG.debug("Request to get all Bids");
        return bidRepository.findAll();
    }

    /**
     * Get one bid by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Bid findOne(Long id) {
        LOG.debug("Request to get Bid : {}", id);
        return bidRepository.findOne(id);
    }

    /**
     * Delete the  bid by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        LOG.debug("Request to delete Bid : {}", id);
        bidRepository.delete(id);
    }
}
