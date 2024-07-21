package org.example.servise;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.PhotosCourseDto;
import org.example.dto.PhotosResponseDto;
import org.example.dto.PhotosUpdateCourseDto;
import org.example.mapper.PhotosMapper;
import org.example.mapper.PhotosResponseMapper;
import org.example.model.Course;
import org.example.model.PhotosCourse;
import org.example.repository.PhotosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class PhotoService {

    private final PhotosMapper photosMapper;
    private final PhotosRepository photosRepository;
    private final PhotosResponseMapper photosResponseMapper;

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public void createPhoto(List<PhotosCourseDto> photos, Course course) {
        List<PhotosCourse> photosCourses = new ArrayList<>();
        for (PhotosCourseDto photo : photos) {
            photo.setCourse(course);
            photosCourses.add(photosMapper.toPhotosCourse(photo));
        }
        photosRepository.saveAll(photosCourses);
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public void updatePhoto(List<PhotosUpdateCourseDto> photosUpdateCourseDto, Course course) {
        List<PhotosCourse> photosCourses = new ArrayList<>();
        for (PhotosUpdateCourseDto photo : photosUpdateCourseDto) {
            PhotosCourse photosCourse = PhotosCourse.builder()
                    .id(photo.getId())
                    .photo(photo.getPhoto())
                    .course(course)
                    .build();
            photosCourses.add(photosCourse);
        }
        photosRepository.saveAll(photosCourses);
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public List<PhotosResponseDto> getPhoto(UUID courseId) {
        List<PhotosCourse> photosCourse = photosRepository.findAllByCourseId(courseId);
        List<PhotosResponseDto> photosResponseDto = new ArrayList<>();
        for (PhotosCourse photo : photosCourse) {
            photosResponseDto.add(photosResponseMapper.toDto(photo));
        }
        return photosResponseDto;
    }
}
