package edu.learn.market.web;

import edu.learn.market.domain.Item;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EditItemController {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(EditItemController.class);

    @GetMapping("/edit-item")
    public String getEdit(Model model) {
        LOG.debug("EditItem get request");
        model.addAttribute("item", new Item());
        return "edit-item";
    }

    @PostMapping("/edit-item")
    public String postEdit(@ModelAttribute @Validated Item item, BindingResult result, Model model) {
        LOG.debug("EditItem post request: " + item);
        if (result.hasErrors()) {
            LOG.warn("Errors: {}", result);
            model.addAttribute("item", item);
        }
        return "edit-item";
    }
}
