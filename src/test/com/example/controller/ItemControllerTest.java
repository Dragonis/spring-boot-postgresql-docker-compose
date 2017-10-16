package com.example.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class ItemControllerTest {

    public String URL = "http://localhost:8080/";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception { }

    @Test
    public void testDataAreRight() throws URISyntaxException {

        // 1 case
        RestTemplate restTemplate = new RestTemplate();
        TestRestTemplate testRestTemplate = new TestRestTemplate(restTemplate);
        ResponseEntity<LinkedList> response = testRestTemplate.getForEntity(
                URL, LinkedList.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        // 2 case
        LinkedList<LinkedHashMap<String,Integer>> body = (LinkedList<LinkedHashMap<String, Integer>>) response.getBody();

        Assert.assertEquals(1, (int) body.get(0).get("id"));
        Assert.assertEquals("A", body.get(0).get("name"));

        Assert.assertEquals(2, (int) body.get(1).get("id"));
        Assert.assertEquals("B", body.get(1).get("name"));

        Assert.assertEquals(3, (int) body.get(2).get("id"));
        Assert.assertEquals("C", body.get(2).get("name"));

/*        // 3 case

        String result = "[{\"id\":1,\"name\":\"A\"},{\"id\":2,\"name\":\"B\"},{\"id\":3,\"name\":\"C\"}]";

        mockRestServiceServer = MockRestServiceServer.createServer((RestTemplate) restTemplate.getForEntity(URL, LinkedList.class));
        mockRestServiceServer.expect(
                requestTo(URL))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(result, APPLICATION_JSON));*/
    }
   

}