package com.san.realestate.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.san.realestate.dto.ApartmentDTO;
import com.san.realestate.entity.Apartment;
import com.san.realestate.service.ApartmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApartmentController.class)
public class ApartmentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApartmentService apartmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateApartment() throws Exception {
        ApartmentDTO dto = new ApartmentDTO();
        Apartment apartment = new Apartment();
        apartment.setId(1L);
        apartment.setUuid(UUID.randomUUID().toString());

        Mockito.when(apartmentService.createApartment(Mockito.any())).thenReturn(apartment);

        mockMvc.perform(post("/apartment/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetAllApartments() throws Exception {
        Mockito.when(apartmentService.filterApartment(null, null))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/apartment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testFindAvailableApartments() throws Exception {
        Mockito.when(apartmentService.filterApartment(
                        Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/apartment/available")
                        .param("dateFrom", "2025-06-01")
                        .param("dateTo", "2025-06-10"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindByUuidFound() throws Exception {
        Apartment apartment = new Apartment();
        apartment.setId(1L);
        apartment.setUuid("test-uuid");

        Mockito.when(apartmentService.findByUUID("test-uuid"))
                .thenReturn(Optional.of(apartment));

        mockMvc.perform(get("/apartment/test-uuid"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value("test-uuid"));
    }

    @Test
    void testFindByUuidNotFound() throws Exception {
        Mockito.when(apartmentService.findByUUID("not-found"))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/apartment/not-found"))
                .andExpect(status().isNotFound());
    }
}
