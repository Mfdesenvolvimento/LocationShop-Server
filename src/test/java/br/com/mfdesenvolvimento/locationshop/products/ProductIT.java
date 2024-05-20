package br.com.mfdesenvolvimento.locationshop.products;

import br.com.mfdesenvolvimento.locationshop.Products.Model.ProductsModel;
import br.com.mfdesenvolvimento.locationshop.Products.Repository.IProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ProductIT {
    @Autowired
    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductsRepository productsRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private UUID productId;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        productId = UUID.randomUUID();
    }


    @Test
    @DisplayName("Get product")
    public void getProduct() {
        ResponseEntity<String> getResponse;
        getResponse = restTemplate.getForEntity("/products", String.class);

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        String ResponseBody = getResponse.getBody();

        System.out.println(ResponseBody);
    }

    @Test
    public void postProduct() {
        String productJson = "{\"nameProduct\": \"Iphone 14 PRO\", \"description\": \"Iphone Seminovo unico dono\", \"price\": \"5000\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(productJson, headers);

        ResponseEntity<String> postResponse = restTemplate.exchange("/products", HttpMethod.POST, request, String.class);

        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        assertNotNull(postResponse);

        System.out.println(postResponse);
    }


    @Test
    void testDeleteProductNotFound() throws Exception {
        // Arrange
        when(productsRepository.findById(productId)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(delete("/products/{id}", productId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Product not found"));
    }

    @Test
    void testDeleteProductSuccess() throws Exception {
        // Arrange
        ProductsModel product = new ProductsModel();
        product.setId(productId);
        when(productsRepository.findById(productId)).thenReturn(Optional.of(product));
        doNothing().when(productsRepository).delete(product);

        // Act & Assert
        mockMvc.perform(delete("/products/{id}", productId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Product deleted successfully"));
    }
}
