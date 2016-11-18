package edu.learn.market.web.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemResourceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllItems() throws Exception {
        this.restTemplate.getForEntity("/api/items", String.class, "sframework");
    }

}