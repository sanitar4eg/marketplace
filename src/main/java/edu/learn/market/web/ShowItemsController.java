package edu.learn.market.web;

import edu.learn.market.domain.Item;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller(value = "/show-items")
public class ShowItemsController {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ShowItemsController.class);

    @GetMapping
    public String showItem(Model model) {
        LOG.debug("ShowItem get request");
        List<Item> items = IntStream.range(1, 10).mapToObj(i -> {
            Item item = new Item();
            item.setDescription(Integer.toString(i));
            return item;
        }).collect(Collectors.toList());
        model.addAttribute("items", items);
        return "show-items";
    }
}
