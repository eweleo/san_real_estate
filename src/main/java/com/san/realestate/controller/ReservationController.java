package com.san.realestate.controller;

import com.san.realestate.dto.ReservationDTO;
import com.san.realestate.entity.Reservation;
import com.san.realestate.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<Reservation>> getAll(@RequestParam(required = false) String customerName, @RequestParam(required = false) LocalDate dateFrom, @RequestParam(required = false) LocalDate dateTo, @RequestParam(required = false) Double priceFrom, @RequestParam(required = false) Double priceTo) {
        return ResponseEntity.ok(reservationService.filter(customerName, dateFrom, dateTo, priceFrom, priceTo));
    }

    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = reservationService.createReservation(reservationDTO);
        if (reservation.getId() != null) {
            return ResponseEntity.created(URI.create("/" + reservation.getId())).body("Reservation " + reservation.getId() + " was created");
        }
        return ResponseEntity.badRequest().body("Something wrong");
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationService.findById(id);
        if (reservation.isPresent()) {
            return ResponseEntity.ok(reservation.get());
        }
        return ResponseEntity.notFound().build();
    }

}
