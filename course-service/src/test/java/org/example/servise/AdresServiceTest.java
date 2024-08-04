package org.example.servise;

import org.example.dto.AddressRequestDto;
import org.example.dto.AddressUpdateRequestDto;
import org.example.mapper.AddressMapper;
import org.example.model.AdressCourse;
import org.example.model.Course;
import org.example.repository.AdressRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты для AddressService, который управляет адресами курсов")
class AdresServiceTest {

    @Mock
    private AdressRepository adressRepository;

    @Mock
    private AddressMapper addressMapper;

    @InjectMocks
    private AdresService adresService;

    @Test
    @DisplayName("Создание адреса с валидными данными, сохраняет адрес")
    void createAddress_WithValidRequest_SavesNewAddress() {
        // given
        UUID courseId = UUID.randomUUID();
        AddressRequestDto addressRequestDto = AddressRequestDto.builder()
                .country("Country")
                .city("City")
                .street("Street")
                .house("House")
                .district("District")
                .build();

        AdressCourse adressCourse = new AdressCourse();
        Course course = new Course();
        course.setId(courseId);
        adressCourse.setCourse(course);

        when(addressMapper.toAdressCourse(addressRequestDto)).thenReturn(adressCourse);

        // when
        adresService.createAddress(addressRequestDto, course);

        // then
        assertFalse(adressCourse.getDelete());
        verify(addressMapper).toAdressCourse(addressRequestDto);
        //verify(adressRepository).save(adressCourse);
        verifyNoMoreInteractions(addressMapper, adressRepository);
    }

    @Test
    @DisplayName("Обновление адреса с валидными данными")
    void updateAddress_WithValidCourseId_UpdatesAddress() {
        // given
        UUID courseId = UUID.randomUUID();
        AddressUpdateRequestDto addressUpdateRequestDto = AddressUpdateRequestDto.builder()
                .country("Country")
                .city("City")
                .street("Street")
                .house("House")
                .district("District")
                .build();

        AdressCourse adressCourse = new AdressCourse();
        Course course = new Course();
        course.setId(courseId);
        adressCourse.setCourse(course);

        when(adressRepository.findByCourseId(courseId)).thenReturn(adressCourse);

        // when
        adresService.updateAddress(addressUpdateRequestDto, courseId);

        // then
        verify(adressRepository).findByCourseId(courseId);
        verify(adressRepository).save(adressCourse);
        verifyNoMoreInteractions(adressRepository);
    }

    @Test
    @DisplayName("Получение адреса по ID курса")
    void getAddress_WithValidCourse_ReturnsAddress() {
        // given
        UUID courseId = UUID.randomUUID();
        AdressCourse adressCourse = new AdressCourse();
        Course course = new Course();
        course.setId(courseId);
        adressCourse.setCourse(course);

        when(adressRepository.findByCourseId(courseId)).thenReturn(adressCourse);

        // when
        AdressCourse result = adresService.getAddress(course);

        // then
        assertNotNull(result);
        assertEquals(adressCourse, result);

        verify(adressRepository).findByCourseId(courseId);
        verifyNoMoreInteractions(adressRepository);
    }

    @Test
    @DisplayName("Удаление адреса по ID курса.")
    void deleteAddress_WithValidCourseId_MarksAsDeleted() {
        // given
        UUID courseId = UUID.randomUUID();
        AdressCourse adressCourse = new AdressCourse();
        Course course = new Course();
        course.setId(courseId);
        adressCourse.setCourse(course);

        when(adressRepository.findByCourseId(courseId)).thenReturn(adressCourse);

        // when
        adresService.deleteAddress(courseId);

        // then
        assertTrue(adressCourse.getDelete());

        verify(adressRepository).findByCourseId(courseId);
        //verify(adressRepository).save(adressCourse);
        verifyNoMoreInteractions(adressRepository);
    }
}