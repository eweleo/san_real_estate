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
    ResponseEntity<?> createApartment(@RequestBody ApartmentDTO apartmentDTO) {
        Apartment apartment = apartmentService.createApartment(apartmentDTO);
        if (apartment.getId() != null) {
            return ResponseEntity.created(URI.create("/" + apartment.getUuid())).body(apartment);
        }
        return ResponseEntity.badRequest().body("Something wrong");
    }

    @GetMapping("")
        ResponseEntity<List<Apartment>>find(@RequestParam(required = false) Double priceFrom, @RequestParam(required = false) Double priceTo){
        return ResponseEntity.ok(apartmentService.filterApartment(priceFrom,priceTo));
        }

    @GetMapping("/available")
    ResponseEntity<List<Apartment>> find(@RequestParam LocalDate dateFrom, @RequestParam LocalDate dateTo,@RequestParam(required = false) Double priceFrom, @RequestParam(required = false) Double priceTo) {
        return ResponseEntity.ok(apartmentService.filterApartment(dateFrom,dateTo,priceFrom,priceTo));
    }

    @GetMapping("/{uuid}")
    ResponseEntity<?> findByUuid(@PathVariable String uuid) {
        Optional<Apartment> apartment = apartmentService.findByUUID(uuid);
        if(apartment.isPresent()){
            return ResponseEntity.ok(apartment.get());
        }
        return (ResponseEntity<?>) ResponseEntity.notFound();
    }
}
