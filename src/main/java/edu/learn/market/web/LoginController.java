package edu.learn.market.web;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class LoginController {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/")
    public String login(Map<String, Object> model) {
        LOG.info("Login get request");
        return "login";
    }
}
