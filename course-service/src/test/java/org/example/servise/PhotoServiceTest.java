package org.example.servise;

import org.example.dto.PhotosCourseDto;
import org.example.dto.PhotosResponseDto;
import org.example.dto.PhotosUpdateCourseDto;
import org.example.mapper.PhotosMapper;
import org.example.mapper.PhotosResponseMapper;
import org.example.model.Course;
import org.example.model.PhotosCourse;
import org.example.repository.PhotosRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты для PhotoService, который управляет фотографиями курсов")
class PhotoServiceTest {

    @Mock
    private PhotosMapper photosMapper;

    @Mock
    private PhotosRepository photosRepository;

    @Mock
    private PhotosResponseMapper photosResponseMapper;

    @InjectMocks
    private PhotoService photoService;

    @Test
    @DisplayName("Создание фотографий с валидными данными, сохраняет фотографии")
    void createPhoto_WithValidData_SavesPhotos() {
        // given
        List<PhotosCourseDto> photosDto = List.of(new PhotosCourseDto("photo", new Course()));
        Course course = new Course();
        PhotosCourse photosCourse = new PhotosCourse();

        when(photosMapper.toPhotosCourse(any())).thenReturn(photosCourse);

        // when
        photoService.createPhoto(photosDto, course);

        // then
        verify(photosRepository).saveAll(anyList());
        verifyNoMoreInteractions(photosRepository);
    }

    @Test
    @DisplayName("Обновление фотографий с валидными данными")
    void updatePhoto_WithValidData_UpdatesPhotos() {
        // given
        List<PhotosUpdateCourseDto> photosUpdateDto = List.of(new PhotosUpdateCourseDto(UUID.randomUUID(), "photo"));
        Course course = new Course();

        // when
        photoService.updatePhoto(photosUpdateDto, course);

        // then
        verify(photosRepository).saveAll(anyList());
        verifyNoMoreInteractions(photosRepository);
    }

    @Test
    @DisplayName("Получение фотографий по ID курса")
    void getPhoto_WithValidCourseId_ReturnsPhotoList() {
        // given
        UUID courseId = UUID.randomUUID();
        List<PhotosCourse> photosCourses = List.of(new PhotosCourse());
        PhotosResponseDto photosResponseDto = new PhotosResponseDto();

        when(photosRepository.findAllByCourseId(courseId)).thenReturn(photosCourses);
        when(photosResponseMapper.toDto(any())).thenReturn(photosResponseDto);

        // when
        List<PhotosResponseDto> result = photoService.getPhoto(courseId);

        // then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(photosRepository).findAllByCourseId(courseId);
        verify(photosResponseMapper).toDto(any());
        verifyNoMoreInteractions(photosRepository, photosResponseMapper);
    }
}