package edu.learn.market.web;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

    @GetMapping("/registration")
    public String registration(Map<String, Object> model) {
        model.put("userName", "");
        model.put("password", "");
        model.put("confirmPassword", "");
        model.put("command", "");

        return "registration";
    }

    @PostMapping("/registration")
    public String doRegister() {

        return "login";
    }
}
