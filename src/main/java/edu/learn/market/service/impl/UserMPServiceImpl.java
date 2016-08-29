package edu.learn.market.service.impl;

import edu.learn.market.domain.UserMP;
import edu.learn.market.repository.UserMPRepository;
import edu.learn.market.service.UserMPService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing UserMP.
 */
@Service
@Transactional
public class UserMPServiceImpl implements UserMPService {

    private final Logger log = LoggerFactory.getLogger(UserMPServiceImpl.class);

    @Inject
    private UserMPRepository userMPRepository;

    /**
     * Save a userMP.
     *
     * @param userMP the entity to save
     * @return the persisted entity
     */
    public UserMP save(UserMP userMP) {
        log.debug("Request to save UserMP : {}", userMP);
        return userMPRepository.save(userMP);
    }

    /**
     * Get all the userMPS.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<UserMP> findAll() {
        log.debug("Request to get all UserMPS");
        return userMPRepository.findAll();
    }

    /**
     * Get one userMP by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public UserMP findOne(Long id) {
        log.debug("Request to get UserMP : {}", id);
        return userMPRepository.findOne(id);
    }

    /**
     * Delete the  userMP by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete UserMP : {}", id);
        userMPRepository.delete(id);
    }
}
