package com.san.realestate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.san.realestate.dto.ReservationDTO;
import com.san.realestate.entity.Reservation;
import com.san.realestate.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateReservation() throws Exception {
        ReservationDTO dto = new ReservationDTO();
        Reservation reservation = new Reservation();
        reservation.setId(1L);

        Mockito.when(reservationService.createReservation(Mockito.any()))
                .thenReturn(reservation);

        mockMvc.perform(post("/reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Reservation 1 was created"));
    }

    @Test
    void testGetAllReservations() throws Exception {
        Mockito.when(reservationService.filter(null, null, null, null, null))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/reservation"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testFindReservationByIdFound() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setId(1L);

        Mockito.when(reservationService.findById(1L))
                .thenReturn(Optional.of(reservation));

        mockMvc.perform(get("/reservation/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindReservationByIdNotFound() throws Exception {
        Mockito.when(reservationService.findById(999L))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/reservation/999"))
                .andExpect(status().isNotFound());
    }
}
