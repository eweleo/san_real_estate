package com.san.realestate.service;

import com.san.realestate.dto.ApartmentDTO;
import com.san.realestate.entity.Apartment;
import com.san.realestate.repository.ApartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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

    public Optional<Apartment> findById(Long id){
        return apartmentRepository.findById(id);
    }

    public Optional<Apartment> findByUUID(String uuid){
        return apartmentRepository.findByUuid(uuid);
    }


    public List<Apartment> filterApartment(LocalDate dateFrom, LocalDate dateTo, Double priceFrom, Double priceTo){
        return apartmentRepository.filterWithDate(dateFrom,dateTo,priceFrom,priceTo);
    }

    public List<Apartment> filterApartment(Double priceFrom, Double priceTo){
        return apartmentRepository.filterWithoutDate(priceFrom, priceTo);
    }

    public void delete(Apartment apartment){
        apartmentRepository.delete(apartment);
    }
}
