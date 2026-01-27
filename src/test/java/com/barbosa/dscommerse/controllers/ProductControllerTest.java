package com.barbosa.dscommerse.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.barbosa.dscommerse.dtos.ProductDTO;
import com.barbosa.dscommerse.factories.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.servlet.function.RequestPredicates.accept;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {

    }

    @Test
    void findAllShouldReturnOkAndPageOfProducts() throws Exception {
        mockMvc.perform(get("/products")
                        .param("page", "0")
                        .param("size", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.page.totalElements").isNumber())
                .andExpect(jsonPath("$.page.totalPages").isNumber())
                .andExpect(jsonPath("$.page.number").value(0));
    }


    @Test
    void findAllWithNameParamShouldReturnFilteredResults() throws Exception {
        mockMvc.perform(get("/products").param("name", "Lord").param("page", "0").param("size", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    void findByIdShouldReturnOkAndProductWhenIdExists() throws Exception {
        mockMvc.perform(get("/products/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.categories").isArray());
    }

    @Test
    void findByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
        mockMvc.perform(get("/products/9999").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createShouldReturnCreatedWhenAdminAndValidDto() throws Exception {
        ProductDTO dto = Factory.createProductDTO();
        String body = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Phone"));
    }

    @Test
    @WithMockUser(roles = "CLIENT")
    void createShouldReturnForbiddenWhenNotAdmin() throws Exception {
        ProductDTO dto = Factory.createProductDTO();
        String body = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateShouldReturnOkWhenAdminAndValidDto() throws Exception {
        ProductDTO dto = Factory.createProductDTO();
        dto.setName("Phone Updated");
        String body = objectMapper.writeValueAsString(dto);

        mockMvc.perform(put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Phone Updated"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
        ProductDTO dto = Factory.createProductDTO();
        String body = objectMapper.writeValueAsString(dto);

        mockMvc.perform(put("/products/9999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteShouldReturnNoContentWhenAdminAndIdExists() throws Exception {
        mockMvc.perform(delete("/products/25").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(roles = "CLIENT")
    void deleteShouldReturnForbiddenWhenNotAdmin() throws Exception {
        mockMvc.perform(delete("/products/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}
