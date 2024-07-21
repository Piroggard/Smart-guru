package org.example.servise;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.*;
import org.example.mapper.*;
import org.example.model.*;
import org.example.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@AllArgsConstructor
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final CourseResponseMapper courseResponseMapper;
    private final AddressResponseMapper addressResponseMapper;
    private final AdresService adresService;
    private final PhotoService photoService;
    private final TechnologyService technologyService;

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public UUID createCourse(CourseCreationDto courseCreationDto) {
        Course course = courseMapper.toCourse(courseCreationDto.getCourse());
        course.setDelete(false);
        Course savedCourse = courseRepository.save(course);
        adresService.createAddress(courseCreationDto.getAddress(), savedCourse);
        photoService.createPhoto(courseCreationDto.getPhotos(), course);
        technologyService.createTechnology(courseCreationDto.getTechnology(), course);
        return savedCourse.getId();
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public UUID updateCourse(CourseUpdateRequestDto courseUpdarionDto) {
        Course course = updateCourse(courseUpdarionDto.getCourse());
        course.setDelete(false);
        adresService.updateAddress(courseUpdarionDto.getAddress(), course.getId());
        technologyService.updateTechnology(courseUpdarionDto.getTechnology(), course.getId());
        photoService.updatePhoto(courseUpdarionDto.getPhotos(), course);
        return course.getId();
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public void deleteCourse(UUID courseId) {
        Course course = findCourseById(courseId);
        course.setDelete(true);
        courseRepository.save(course);
        adresService.deleteAddress(course.getId());
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public CourseResponseDto getCourses(UUID courseId) {
        Course course = findCourseById(courseId);
        CourseResponseDto courseResponseDto = courseResponseMapper.toCourseDto(courseRepository.findById(courseId).get());
        AdressCourse adressCourse = adresService.getAddress(course);
        courseResponseDto.setAddress(addressResponseMapper.toDto(adressCourse));
        courseResponseDto.setTechnology(technologyService.getTechnology(course));
        courseResponseDto.setPhotos(photoService.getPhoto(course.getId()));
        return courseResponseDto;
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public Course updateCourse(CourseUpdateDto courseUpdate) {
        Course course = findCourseById(courseUpdate.getId());
        course.setName(courseUpdate.getName());
        course.setType(courseUpdate.getType());
        course.setNumberSeats(courseUpdate.getNumberSeats());
        course.setPrice(courseUpdate.getPrice());
        course.setPhotoProfile(courseUpdate.getPhotoProfile());
        course.setDirection(courseUpdate.getDirection());
        course.setDescription(courseUpdate.getDescription());
        course.setDuration(courseUpdate.getDuration());
        course.setStatus(courseUpdate.getStatus());
        course.setWhatLearn(courseUpdate.getWhatLearn());
        course.setCertificate(courseUpdate.getCertificate());
        return course;
    }

    private Course findCourseById(UUID id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + id));
    }
}
