package com.san.realestate.repository;

import com.san.realestate.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("""
        select r from Reservation r where (r.customerName = :customerName or :customerName is null)
        and (r.dateFrom >= :dateFrom or :dateFrom is null) and (r.dateTo <= :dateTo or :dateTo is null)
        and (r.fullPrice >= :priceFrom or :priceFrom is null) and (r.fullPrice <= :priceTo or :priceTo is null)
""")
    List<Reservation> filter(String customerName,
                             LocalDate dateFrom,
                             LocalDate dateTo,
                             Double priceFrom,
                             Double priceTo);
}
