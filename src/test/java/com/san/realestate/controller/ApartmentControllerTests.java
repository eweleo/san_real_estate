package com.san.realestate.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.san.realestate.dto.ApartmentDTO;
import com.san.realestate.entity.Apartment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApartmentControllerTests {

    private final HttpClient client = HttpClient.newHttpClient();
    private Apartment apartment;
    @Test
    void createApartment_badRequest() throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/apartment/create"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(400,response.statusCode());
    }

    @Test
    void createApartment_ok() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ApartmentDTO apartment = new ApartmentDTO();
        apartment.setDescription("description");
        apartment.setTitle("title");
        apartment.setPrice(22.33);
        String jsonBody = objectMapper.writeValueAsString(apartment);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/apartment/create"))
                .header("Content-Type", "application/json")
                .POST(java.net.http.HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(201,response.statusCode());
        Assertions.assertNotNull(objectMapper.readTree(response.body()));
    }



}
