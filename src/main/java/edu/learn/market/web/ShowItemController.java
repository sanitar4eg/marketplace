package edu.learn.market.web;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowItemController {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ShowItemController.class);

    @GetMapping("/show-item")
    public String showItem(Model model) {

        return "show-item";
    }
}
