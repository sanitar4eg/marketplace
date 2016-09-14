package edu.learn.market.web;

import edu.learn.market.domain.UserMP;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

    @GetMapping("/registration")
    public String registration(Model model) {
        LOG.debug("Registration get request");
        model.addAttribute("userMP", new UserMP());
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(@ModelAttribute @Validated UserMP userMP, BindingResult result, Model model) {
        LOG.debug("Registration post request");
        if (result.hasErrors()) {
            LOG.warn("Errors", result);
            model.addAttribute("userMP", userMP);
            return "registration";
        }

        return "login";
    }
}
