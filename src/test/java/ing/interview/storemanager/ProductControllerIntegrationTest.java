package ing.interview.storemanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import ing.interview.storemanager.dto.product.ProductDto;
import ing.interview.storemanager.dto.product.ProductDtoBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void testAddProduct_returnDto() throws Exception{

        ProductDto productDto = ProductDtoBuilder.builder()
                .withName("Test Product")
                .withDescription("Test desc")
                .withPrice(99.99)
                .withStock(150)
                .build();

        mockMvc.perform(post("/products/add")
                .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Test Product"));

    }

}
