package com.barbosa.dscommerse.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.barbosa.dscommerse.dtos.OrderDTO;
import com.barbosa.dscommerse.factories.Factory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "maria@gmail.com", roles = "CLIENT")
    void findByIdShouldReturnOkWhenClientAndOwnOrder() throws Exception {
        mockMvc.perform(get("/orders/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.items").isArray());
    }

    @Test
    @WithMockUser(username = "alex@gmail.com", roles = "ADMIN")
    void findByIdShouldReturnOkWhenAdmin() throws Exception {
        mockMvc.perform(get("/orders/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @WithMockUser(username = "maria@gmail.com", roles = "CLIENT")
    void findByIdShouldReturnForbiddenWhenClientAccessesOtherOrder() throws Exception {
        mockMvc.perform(get("/orders/2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "maria@gmail.com", roles = "CLIENT")
    void findByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
        mockMvc.perform(get("/orders/9999").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "maria@gmail.com", roles = "CLIENT")
    void insertShouldReturnCreatedWhenValidOrder() throws Exception {
        OrderDTO dto = Factory.createOrderDTO();
        dto.setId(null);
        dto.setMoment(null);
        dto.setStatus(null);
        dto.setClient(null);
        String body = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.items").isArray());
    }

    @Test
    void findByIdShouldReturnUnauthorizedWhenNotAuthenticated() throws Exception {
        mockMvc.perform(get("/orders/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}
