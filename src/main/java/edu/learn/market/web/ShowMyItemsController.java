package edu.learn.market.web;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowMyItemsController {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ShowMyItemsController.class);

    @GetMapping("/show-my-items")
    public String showMyItem(Model model) {

        return "show-my-items";
    }
}
