package com.san.realestate.service;

import com.san.realestate.dto.ApartmentDTO;
import com.san.realestate.entity.Apartment;
import com.san.realestate.repository.ApartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;

    public Apartment createApartment(ApartmentDTO apartmentDTO){
        Random random = new Random();
        Apartment apartment = Apartment.builder()
                .uuid(String.valueOf(new UUID(random.nextLong(), random.nextLong())))
                .title(apartmentDTO.getTitle())
                .description(apartmentDTO.getDescription())
                .price(apartmentDTO.getPrice())
                .build();
        apartmentRepository.save(apartment);
        return apartment;
    }

    public List<Apartment> findAll(){
        return apartmentRepository.findAll();
    }

    public Optional<Apartment> findByUUID(String uuid){
        return apartmentRepository.findByUuid(uuid);
    }


    public List<Apartment> filterApartment(double priceFrom, double priceTo, LocalDate dateFrom, LocalDate dateTo){
        return
    }
}
