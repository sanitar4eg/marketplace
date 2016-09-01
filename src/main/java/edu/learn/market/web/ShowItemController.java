package edu.learn.market.web;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class ShowItemController {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ShowItemController.class);

    @GetMapping("/show-item")
    public String showItem(Map<String, Object> model) {

        return "showItem";
    }
}
