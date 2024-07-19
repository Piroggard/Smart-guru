package org.example.servise;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.AddressRequestDto;
import org.example.dto.AddressUpdateRequestDto;
import org.example.mapper.AddressMapper;
import org.example.model.AdressCourse;
import org.example.model.Course;
import org.example.repository.AdressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
public class AdresService {
    private final AdressRepository adressRepository;
    private final AddressMapper addressMapper;

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public void updateAddress( AddressUpdateRequestDto address, Course course) {
        AdressCourse adressCourse = adressRepository.findAdressCourseByCourse(course);
        adressCourse = AdressCourse.builder()
                .id(address.getId())
                .course(course)
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getStreet())
                .house(address.getHouse())
                .district(address.getDistrict())
                .build();
        adressRepository.save(adressCourse);
        log.info("Обновляем " , adressCourse);
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public AdressCourse getAddress (Course course){
        return adressRepository.findAdressCourseByCourse(course);
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public void deleteAddress (Course course){
        AdressCourse adressCourse = adressRepository.findAdressCourseByCourse(course);
        adressCourse.setDelete(true);
        adressRepository.save(adressCourse);
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public void createAddress ( AddressRequestDto address, Course course){
        AdressCourse adressCourse = addressMapper.toAdressCourse(address);
        adressCourse.setCourse(course);
        adressCourse.setDelete(false);
        log.info("Сохраняем " +  adressCourse);
        adressRepository.save(adressCourse);
    }
}
