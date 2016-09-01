package edu.learn.market.web;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class ShowMyItemController {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ShowMyItemController.class);

    @GetMapping("/show-my-item")
    public String showMyItem(Map<String, Object> model) {

        return "showMyItem";
    }
}
