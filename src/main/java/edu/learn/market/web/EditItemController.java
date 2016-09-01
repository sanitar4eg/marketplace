package edu.learn.market.web;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class EditItemController {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(EditItemController.class);

    @GetMapping("/edit-item")
    public String editItem(Map<String, Object> model) {

        return "editItem";
    }
}
