package br.com.mfdesenvolvimento.locationshop.products;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIT {
    @Autowired
    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void getProduct() {
        ResponseEntity<String> getResponse;
        getResponse = restTemplate.getForEntity("/products", String.class);

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        String ResponseBody = getResponse.getBody();

        System.out.println(ResponseBody);
    }

    @Test
    public void postProduct() {
        ResponseEntity<String> postResponse;
        postResponse = restTemplate.getForEntity("/products", String.class);

        assertNotNull(postResponse.equals(postResponse));

        System.out.println(postResponse);
    }

}
