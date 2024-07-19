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

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class PhotoService {

    private final PhotosMapper photosMapper;
    private final PhotosRepository photosRepository;
    private final PhotosResponseMapper photosResponseMapper;

    public void createPhoto(List<PhotosCourseDto> photos, Course course) {
        List<PhotosCourse> photosCourses = new ArrayList<>();
        for (PhotosCourseDto photo : photos) {
            photo.setCourse(course);
            photosCourses.add(photosMapper.toPhotosCourse(photo));
        }
        photosRepository.saveAll(photosCourses);
    }

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

    public List<PhotosResponseDto> getPhoto(Course course) {
        List<PhotosCourse> photosCourse = photosRepository.findAllByCourse(course);
        List<PhotosResponseDto> photosResponseDto = new ArrayList<>();
        for (PhotosCourse photo : photosCourse) {
            photosResponseDto.add(photosResponseMapper.toDto(photo));
        }
        return photosResponseDto;
    }
}
