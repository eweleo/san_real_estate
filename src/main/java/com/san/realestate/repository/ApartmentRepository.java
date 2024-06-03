package com.san.realestate.repository;

import com.san.realestate.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    Optional<Apartment> findByUuid(String uuid);

    @Query("select a from Apartment a left join Reservation r on r.apartment = a where " +
            "(a.price >= :priceFrom or :priceFrom is null) and (a.price <= :priceTo or :priceTo is null)" +
            "and (:dateFrom )" +
            "and (r. )")
    List<Apartment> getAvailableApartment(double priceFrom, double priceTo, LocalDate dateFrom, LocalDate dateTo);
}
