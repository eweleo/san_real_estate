package com.san.realestate.service;

import com.san.realestate.dto.ReservationDTO;
import com.san.realestate.entity.Apartment;
import com.san.realestate.entity.Reservation;
import com.san.realestate.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ApartmentService apartmentService;

    public Reservation createReservation(ReservationDTO reservationDTO){
        Optional<Apartment> apartment = apartmentService.findById(reservationDTO.getApartmentId());
        if(apartment.isPresent()) {
            Reservation reservation = Reservation.builder()
                    .apartment(apartment.get())
                    .customerName(reservationDTO.getUsername())
                    .dateFrom(reservationDTO.getDateFrom())
                    .dateTo(reservationDTO.getDateTo())
                    .fullPrice(calculatePrice(reservationDTO)).build();

            reservationRepository.save(reservation);
            return reservation;
        }
        return new Reservation();
    }

    public List<Reservation> filter(String customerName,
                                    LocalDate dateFrom,
                                    LocalDate dateTo,
                                    Double priceFrom,
                                    Double priceTo){
        return reservationRepository.filter(customerName, dateFrom, dateTo, priceFrom, priceTo);
    }

    private double calculatePrice(ReservationDTO reservationDTO){
        return apartmentService.findById(reservationDTO.getApartmentId()).get().getPrice() * Duration.between(reservationDTO.getDateFrom(), reservationDTO.getDateTo()).toDays();
    }

    public Optional<Reservation> findById(Long id){
        return reservationRepository.findById(id);
    }

    public void delete (Reservation reservation){
        reservationRepository.delete(reservation);
    }
}
