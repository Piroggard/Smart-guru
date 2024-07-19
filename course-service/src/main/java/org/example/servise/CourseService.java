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
    private final OrganizerRepository repositoryOrganizer;
    private final CourseMapper courseMapper;
    private final CourseResponseMapper courseResponseMapper;
    private final AddressResponseMapper addressResponseMapper;
    private final AdresService adresService;
    private final PhotoService photoService;
    private final TechnologyService technologyService;

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public UUID createCourse(CourseCreationDto courseCreationDTO) {
        Course course = courseMapper.toCourse(courseCreationDTO.getCourse());
        course.setDelete(false);
        Course savedCourse = courseRepository.save(course);
        adresService.createAddress(courseCreationDTO.getAddress(), savedCourse);
        photoService.createPhoto(courseCreationDTO.getPhotos(), course);
        technologyService.createTechnology(courseCreationDTO.getTechnology(), course);
        return savedCourse.getId();
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public UUID updateCourse(CourseUpdateRequestDto courseUpdarionDto) {
        Course existingCourse = findCourseById(courseUpdarionDto.getCourse().getId());
        Organizer organizer = findOrganizerById(existingCourse.getOrganizerId().getId());
        Course course = getCourse(courseUpdarionDto.getCourse(), organizer);
        course.setDelete(false);
        adresService.updateAddress(courseUpdarionDto.getAddress(), course);
        technologyService.updateTechnology(courseUpdarionDto.getTechnology(), course);
        photoService.updatePhoto(courseUpdarionDto.getPhotos(), course);
        return course.getId();
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public void deleteCourse(UUID courseId) {
        Course course = findCourseById(courseId);
        courseRepository.save(course);
        adresService.deleteAddress(course);
    }

    @Transactional
    public CourseResponseDto getCourses(UUID courseId) {
        Course course = findCourseById(courseId);
        CourseResponseDto courseResponseDto = courseResponseMapper.toCourseDto(courseRepository.findById(courseId).get());
        AdressCourse adressCourse = adresService.getAddress(course);
        courseResponseDto.setAddress(addressResponseMapper.toDto(adressCourse));
        courseResponseDto.setTechnology(technologyService.getTechnology(course));
        courseResponseDto.setPhotos(photoService.getPhoto(course));
        return courseResponseDto;
    }

    private Course findCourseById(UUID id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + id));
    }

    private Organizer findOrganizerById(UUID id) {
        return repositoryOrganizer.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organizer not found with id: " + id));
    }

    public Course getCourse(CourseUpdateDto courseUpdateDto, Organizer organizer) {
        Course course = Course.builder()
                .id(courseUpdateDto.getId())
                .name(courseUpdateDto.getName())
                .url(courseUpdateDto.getUrl())
                .type(courseUpdateDto.getType())
                .numberSeats(courseUpdateDto.getNumberSeats())
                .price(courseUpdateDto.getPrice())
                .photoProfile(courseUpdateDto.getPhotoProfile())
                .direction(courseUpdateDto.getDirection())
                .description(courseUpdateDto.getDescription())
                .duration(courseUpdateDto.getDuration())
                .status(courseUpdateDto.getStatus())
                .whatLearn(courseUpdateDto.getWhatLearn())
                .certificate(courseUpdateDto.getCertificate())
                .organizerId(organizer)
                .build();
        return course;
    }
}
