package org.example.servise;

import org.example.dto.TechnologyRequestDto;
import org.example.dto.TechnologyResponseDto;
import org.example.dto.TechnologyUpdateRequestDto;
import org.example.mapper.TechnologyMapper;
import org.example.mapper.TechnologyResponseMapper;
import org.example.model.Course;
import org.example.model.Technology;
import org.example.repository.TechnologyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты для TechnologyService, который управляет технологиями курсов")
class TechnologyServiceTest {

    @Mock
    private TechnologyMapper technologyMapper;

    @Mock
    private TechnologyRepository technologyRepository;

    @Mock
    private TechnologyResponseMapper technologyResponseMapper;

    @InjectMocks
    private TechnologyService technologyService;

    @Test
    @DisplayName("Создание технологии с валидными данными, сохраняет технологию")
    void createTechnology_WithValidData_SavesTechnology() {
        // given
        TechnologyRequestDto requestDto = TechnologyRequestDto.builder()
                .name("Java")
                .photo("photo.jpg")
                .build();
        Course course = new Course();
        Technology technology = new Technology();
        technology.setCourse(course);

        when(technologyMapper.toTechnology(requestDto)).thenReturn(technology);

        // when
        technologyService.createTechnology(requestDto, course);

        // then
        verify(technologyMapper).toTechnology(requestDto);
        verify(technologyRepository).save(technology);
        verifyNoMoreInteractions(technologyMapper, technologyRepository);
    }

    @Test
    @DisplayName("Обновление технологии с валидными данными")
    void updateTechnology_WithExistingTechnology_UpdatesTechnology() {
        // given
        UUID courseId = UUID.randomUUID();
        TechnologyUpdateRequestDto updateDto = TechnologyUpdateRequestDto.builder()
                .name("Python")
                .photo("photo_updated.jpg")
                .build();

        Technology technology = new Technology();
        technology.setId(courseId);

        when(technologyRepository.findTechnologiesByCourseId(courseId)).thenReturn(technology);

        // when
        technologyService.updateTechnology(updateDto, courseId);

        // then
        assertEquals("Python", technology.getName());
        assertEquals("photo_updated.jpg", technology.getPhoto());
        verify(technologyRepository).findTechnologiesByCourseId(courseId);
        //verify(technologyRepository).save(technology);
        verifyNoMoreInteractions(technologyRepository);
    }

    @Test
    @DisplayName("Получение технологии по ID курса")
    void getTechnology_WithValidCourse_ReturnsTechnologyResponseDto() {
        // given
        UUID courseId = UUID.randomUUID();
        Course course = new Course();
        course.setId(courseId);
        Technology technology = new Technology();
        TechnologyResponseDto responseDto = new TechnologyResponseDto();

        when(technologyRepository.findTechnologiesByCourseId(courseId)).thenReturn(technology); // Используем courseId
        when(technologyResponseMapper.toDto(technology)).thenReturn(responseDto);

        // when
        TechnologyResponseDto result = technologyService.getTechnology(course);

        // then
        assertNotNull(result);
        assertEquals(responseDto, result);

        verify(technologyRepository).findTechnologiesByCourseId(courseId);
        verify(technologyResponseMapper).toDto(technology);
        verifyNoMoreInteractions(technologyRepository, technologyResponseMapper);
    }
}