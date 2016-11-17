package edu.learn.market.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ShowItemsController.class)
public class ShowItemsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser
    public void showItem() throws Exception {
        this.mvc.perform(get("/show-items").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());

    }

}