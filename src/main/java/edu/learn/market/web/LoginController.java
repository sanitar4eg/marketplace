package edu.learn.market.web;

import edu.learn.market.domain.UserMP;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.info.InfoPropertiesInfoContributor;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class LoginController {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String getLogin(Model model) {
        LOG.debug("Login get request");
        model.addAttribute("userMP", new UserMP());
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute @Validated UserMP userMP, BindingResult result, Model model) {
        LOG.debug("Login post request: " + userMP);
        if (result.hasErrors()) {
            LOG.warn("Errors", result);
            model.addAttribute("userMP", userMP);
        }
        return "login";
    }
}
