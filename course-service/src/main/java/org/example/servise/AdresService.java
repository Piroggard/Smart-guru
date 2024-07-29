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

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class AdresService {
    private final AdressRepository adressRepository;
    private final AddressMapper addressMapper;

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public void updateAddress(AddressUpdateRequestDto address, UUID courseId) {
        AdressCourse adressCourse = adressRepository.findByCourseId(courseId);
        adressCourse.setCountry(address.getCountry());
        adressCourse.setCity(address.getCity());
        adressCourse.setCity(address.getStreet());
        adressCourse.setHouse(address.getHouse());
        adressCourse.setDistrict(address.getDistrict());
        adressRepository.save(adressCourse);
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public AdressCourse getAddress (Course course){
        return adressRepository.findByCourseId(course.getId());
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public void deleteAddress (UUID courseId){
        AdressCourse adressCourse = adressRepository.findByCourseId(courseId);
        adressCourse.setDelete(true);
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public void createAddress ( AddressRequestDto address, Course course){
        AdressCourse adressCourse = addressMapper.toAdressCourse(address);
        adressCourse.setCourse(course);
        adressCourse.setDelete(false);
    }
}
