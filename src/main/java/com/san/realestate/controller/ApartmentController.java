package com.san.realestate.controller;

import com.san.realestate.dto.ApartmentDTO;
import com.san.realestate.entity.Apartment;
import com.san.realestate.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/apartment")
public class ApartmentController {
    private final ApartmentService apartmentService;

    @PostMapping("/create")
    ResponseEntity<String> createApartment(@RequestBody ApartmentDTO apartmentDTO) {
        Apartment apartment = apartmentService.createApartment(apartmentDTO);
        if (apartment.getId() != null) {
            return ResponseEntity.created(URI.create("/" + apartment.getUuid())).body("Apartment is crated");
        }
        return ResponseEntity.badRequest().body("Something wrong");
    }

    @GetMapping("")
    ResponseEntity<List<Apartment>> findAll(@RequestParam(required = false) double priceFrom, @RequestParam(required = false) double priceTo,
                                            @RequestParam(required = false) LocalDate dateFrom, @RequestParam(required = false) LocalDate dateTo) {
        return ResponseEntity.ok(apartmentService.findAll());
    }

    @GetMapping("/{uuid}")
    ResponseEntity<Apartment> findByUuid(@PathVariable String uuid) {
        Optional<Apartment> apartment = apartmentService.findByUUID(uuid);
        return apartment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
