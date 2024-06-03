package com.san.realestate.controller;

import com.san.realestate.dto.ReservationDTO;
import com.san.realestate.entity.Reservation;
import com.san.realestate.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<Reservation>> getAll(){
        return ResponseEntity.ok(reservationService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody ReservationDTO reservationDTO){
        Reservation reservation = reservationService.createReservation(reservationDTO);
        if(reservation.getId() != null){
            return ResponseEntity.created(URI.create("/" + reservation.getId())).body("Reservation " + reservation.getId() + " was created");
        }
        return ResponseEntity.badRequest().body("Something wrong");
    }


}
