package org.example.servise;

import org.example.dto.*;
import org.example.mapper.AddressResponseMapper;
import org.example.mapper.CourseMapper;
import org.example.mapper.CourseResponseMapper;
import org.example.model.AdressCourse;
import org.example.model.Course;
import org.example.repository.CourseRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты для CourseService, который управляет курсами")
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseMapper courseMapper;

    @Mock
    private CourseResponseMapper courseResponseMapper;

    @Mock
    private AddressResponseMapper addressResponseMapper;

    @Mock
    private AdresService adresService;

    @Mock
    private PhotoService photoService;

    @Mock
    private TechnologyService technologyService;

    @InjectMocks
    private CourseService courseService;

    @Test
    @DisplayName("Создание курса с валидными данными, возвращает ID курса")
    void createCourse_WithValidData_ReturnsCourseId() {
        // given
        UUID courseId = UUID.randomUUID();
        CourseCreationDto courseCreationDto = CourseCreationDto.builder()
                .course(new CourseRequestDto())
                .address(new AddressRequestDto())
                .photos(List.of(new PhotosCourseDto()))
                .technology(new TechnologyRequestDto())
                .build();

        Course course = new Course();
        course.setId(courseId);

        when(courseMapper.toCourse(courseCreationDto.getCourse())).thenReturn(course);
        when(courseRepository.save(course)).thenReturn(course);

        // when
        UUID result = courseService.createCourse(courseCreationDto);

        // then
        assertEquals(courseId, result);

        verify(courseMapper).toCourse(courseCreationDto.getCourse());
        verify(courseRepository).save(course);
        verify(adresService).createAddress(courseCreationDto.getAddress(), course);
        verify(photoService).createPhoto(courseCreationDto.getPhotos(), course);
        verify(technologyService).createTechnology(courseCreationDto.getTechnology(), course);
        verifyNoMoreInteractions(courseRepository, adresService, photoService, technologyService);
    }

    @Test
    @DisplayName("Обновление курса с валидными данными")
    void updateCourse_WithValidData_UpdatesCourse() {
        // given
        UUID courseId = UUID.randomUUID();
        CourseUpdateRequestDto courseUpdateRequestDto = CourseUpdateRequestDto.builder()
                .course(CourseUpdateDto.builder().id(courseId).build())
                .address(new AddressUpdateRequestDto())
                .photos(List.of(new PhotosUpdateCourseDto()))
                .technology(new TechnologyUpdateRequestDto())
                .build();

        Course course = new Course();
        course.setId(courseId);

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        // when
        UUID result = courseService.updateCourse(courseUpdateRequestDto);

        // then
        assertEquals(courseId, result);

        verify(courseRepository).findById(courseUpdateRequestDto.getCourse().getId());
        verify(adresService).updateAddress(courseUpdateRequestDto.getAddress(), course.getId());
        verify(photoService).updatePhoto(courseUpdateRequestDto.getPhotos(), course);
        verify(technologyService).updateTechnology(courseUpdateRequestDto.getTechnology(), course.getId());
        verifyNoMoreInteractions(courseRepository, adresService, photoService, technologyService);
    }

    @Test
    @DisplayName("Получение курса по ID")
    void getCourses_WithValidCourseId_ReturnsCourseResponseDto() {
        // given
        UUID courseId = UUID.randomUUID();
        Course course = new Course();
        course.setId(courseId);
        CourseResponseDto courseResponseDto = new CourseResponseDto();

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(courseResponseMapper.toCourseDto(course)).thenReturn(courseResponseDto);
        when(adresService.getAddress(course)).thenReturn(new AdressCourse());
        when(addressResponseMapper.toDto(any())).thenReturn(new AddressResponseDto());
        when(technologyService.getTechnology(course)).thenReturn(new TechnologyResponseDto());
        when(photoService.getPhoto(courseId)).thenReturn(List.of(new PhotosResponseDto()));

        // when
        CourseResponseDto result = courseService.getCourses(courseId);

        // then
        assertEquals(courseResponseDto, result);

        verify(courseRepository, times(2)).findById(courseId);
        verify(courseResponseMapper).toCourseDto(course);
        verify(adresService).getAddress(course);
        verify(addressResponseMapper).toDto(any());
        verify(technologyService).getTechnology(course);
        verify(photoService).getPhoto(courseId);
        verifyNoMoreInteractions(courseRepository, adresService, addressResponseMapper, technologyService, photoService);
    }

    @Test
    @DisplayName("Удаление курса по ID")
    void deleteCourse_WithValidCourseId_DeletesCourse() {
        // given
        UUID courseId = UUID.randomUUID();
        Course course = new Course();
        course.setId(courseId);

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        // when
        courseService.deleteCourse(courseId);

        // then
        verify(courseRepository).findById(courseId);
        verify(courseRepository).save(course);
        verify(adresService).deleteAddress(courseId);
        verifyNoMoreInteractions(courseRepository, adresService);
    }
}