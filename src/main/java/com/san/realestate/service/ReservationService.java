package com.san.realestate.service;

import com.san.realestate.dto.ReservationDTO;
import com.san.realestate.entity.Apartment;
import com.san.realestate.entity.Customer;
import com.san.realestate.entity.Reservation;
import com.san.realestate.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ApartmentService apartmentService;
    private final CustomerService customerService;

    public Reservation createReservation(ReservationDTO reservationDTO){
        Optional<Apartment> apartment = apartmentService.findById(reservationDTO.getApartmentId());
        Optional<Customer> customer = customerService.findByUsername(reservationDTO.getUsername());
        if(apartment.isPresent() && customer.isPresent()) {
            Reservation reservation = Reservation.builder()
                    .apartment(apartment.get())
                    .customer(customer.get())
                    .dateFrom(reservationDTO.getDateFrom())
                    .dateTo(reservationDTO.getDateTo())
                    .fullPrice(calculatePrice(reservationDTO)).build();

            reservationRepository.save(reservation);
            return reservation;
        }
        return new Reservation();
    }

    public List<Reservation> findAll(){
        return reservationRepository.findAll();
    }

    private double calculatePrice(ReservationDTO reservationDTO){
        return apartmentService.findById(reservationDTO.getApartmentId()).get().getPrice() * Duration.between(reservationDTO.getDateFrom(), reservationDTO.getDateTo()).toDays();
    }
}
