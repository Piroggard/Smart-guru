package org.example.controller;

import org.example.dto.*;
import org.example.servise.CourseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Модульные тесты для CourseController")
class CourseControllerTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    @Test
    @DisplayName("createCourse создаст курс и вернет ID созданного курса")
    void createCourse_ValidRequest_ReturnsUUID() {
        // given
        CourseRequestDto courseRequestDto = new CourseRequestDto();
        courseRequestDto.setName("Test Course");
        CourseCreationDto courseCreationDto = new CourseCreationDto();
        courseCreationDto.setCourse(courseRequestDto);

        UUID expectedUUID = UUID.randomUUID();
        when(courseService.createCourse(courseCreationDto)).thenReturn(expectedUUID);

        // when
        UUID result = courseController.createCourse(courseCreationDto);

        // then
        assertEquals(expectedUUID, result);
        verify(courseService).createCourse(courseCreationDto);
        verifyNoMoreInteractions(courseService);
    }

    @Test
    @DisplayName("updateCourse обновит курс и вернет ID обновленного курса")
    void updateCourse_ValidRequest_ReturnsUUID() {
        // given
        CourseUpdateDto courseUpdateDto = new CourseUpdateDto();
        courseUpdateDto.setName("Updated Course");
        CourseUpdateRequestDto courseUpdateRequestDto = new CourseUpdateRequestDto();
        courseUpdateRequestDto.setCourse(courseUpdateDto);

        UUID expectedUUID = UUID.randomUUID();
        when(courseService.updateCourse(courseUpdateRequestDto)).thenReturn(expectedUUID);

        // when
        UUID result = courseController.updateCourse(courseUpdateRequestDto);

        // then
        assertEquals(expectedUUID, result);

        verify(courseService).updateCourse(courseUpdateRequestDto);
        verifyNoMoreInteractions(courseService);
    }

    @Test
    @DisplayName("deleteCourse удаляет курс по ID")
    void deleteCourse_ValidRequest_DeletesCourse() {
        // given
        UUID courseId = UUID.randomUUID();

        // when
        courseController.deleteCourse(courseId);

        // then
        verify(courseService).deleteCourse(courseId);
        verifyNoMoreInteractions(courseService);
    }

    @Test
    @DisplayName("getCourses возвращает курс по ID")
    void getCourses_ValidRequest_ReturnsCourse() {
        // given
        UUID courseId = UUID.randomUUID();
        CourseResponseDto expectedCourse = new CourseResponseDto();
        when(courseService.getCourses(courseId)).thenReturn(expectedCourse);

        // when
        CourseResponseDto result = courseController.getCourses(courseId);

        // then
        assertEquals(expectedCourse, result);

        verify(courseService).getCourses(courseId);
        verifyNoMoreInteractions(courseService);
    }
}