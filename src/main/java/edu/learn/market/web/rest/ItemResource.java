package edu.learn.market.web.rest;

import edu.learn.market.domain.Item;
import edu.learn.market.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemResource {

    private final Logger log = LoggerFactory.getLogger(ItemResource.class);

    @Inject
    private ItemService itemService;

    /**
     * GET  /items : get all the items.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of items in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems()
            throws URISyntaxException {
        log.debug("REST request to get all Items");
        List<Item> items = itemService.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
