package org.example.servise;

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
    private final AdressRepository adressRepository;
    private final PhotosRepository photosRepository;
    private final TechnologyRepository technologyRepository;
    private final OrganizerRepository repositoryOrganizer;
    private final CourseMapper courseMapper;
    private final CourseResponseMapper courseResponseMapper;
    private final AddressMapper addressMapper;
    private final PhotosMapper photosMapper;
    private final TechnologyMapper technologyMapper;
    private final TechnologyResponseMapper technologyResponseMapper;
    private final PhotosResponseMapper photosResponseMapper;
    private final AddressResponseMapper addressResponseMapper;

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public UUID createCourse(CourseCreationDto courseCreationDTO) {
        Course course = courseMapper.toCourse(courseCreationDTO.getCourse());
        log.info("Data that we save in the database after mapping {}", course);
        Course savedCourse = courseRepository.save(course);
        AddressRequestDto addressRequestDto = courseCreationDTO.getAddress();
        AdressCourse adressCourse = addressMapper.toAdressCourse(addressRequestDto);
        adressCourse.setCourse(savedCourse);
        adressRepository.save(adressCourse);
        List<PhotosCourse> photosCourses = new ArrayList<>();
        for (PhotosCourseDto photo : courseCreationDTO.getPhotos()) {
            photo.setCourse(savedCourse);
            photosCourses.add(photosMapper.toPhotosCourse(photo));
        }
        photosRepository.saveAll(photosCourses);
        TechnologyRequestDto technologyRequestDto = courseCreationDTO.getTechnology();
        log.info("Data that we save in the database after mapping {}", technologyRequestDto);
        Technology technology = technologyMapper.toTechnology(technologyRequestDto);
        technology.setCourse(savedCourse);
        technologyRepository.save(technology);
        return savedCourse.getId();
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public UUID updateCourse(CourseUpdateRequestDto courseUpdarionDto) {
        Course existingCourse  = findCourseById(courseUpdarionDto.getCourse().getId());
        Organizer organizer = findOrganizerById(existingCourse.getOrganizerId().getId());
        Course course = Course.builder()
                .id(courseUpdarionDto.getCourse().getId())
                .name(courseUpdarionDto.getCourse().getName())
                .url(courseUpdarionDto.getCourse().getUrl())
                .type(courseUpdarionDto.getCourse().getType())
                .numberSeats(courseUpdarionDto.getCourse().getNumberSeats())
                .price(courseUpdarionDto.getCourse().getPrice())
                .photoProfile(courseUpdarionDto.getCourse().getPhotoProfile())
                .direction(courseUpdarionDto.getCourse().getDirection())
                .description(courseUpdarionDto.getCourse().getDescription())
                .duration(courseUpdarionDto.getCourse().getDuration())
                .status(courseUpdarionDto.getCourse().getStatus())
                .whatLearn(courseUpdarionDto.getCourse().getWhatLearn())
                .certificate(courseUpdarionDto.getCourse().getCertificate())
                .organizerId(organizer)
                .build();
        log.info("Data that we save in the database after mapping {}", course);
        AdressCourse adressCourse = AdressCourse.builder()
                .id(courseUpdarionDto.getAddress().getId())
                .course(course)
                .country(courseUpdarionDto.getAddress().getCountry())
                .city(courseUpdarionDto.getAddress().getCity())
                .street(courseUpdarionDto.getAddress().getStreet())
                .house(courseUpdarionDto.getAddress().getHouse())
                .district(courseUpdarionDto.getAddress().getDistrict())
                .build();
        adressRepository.save(adressCourse);
        Technology technology = Technology.builder()
                .id(courseUpdarionDto.getTechnology().getId())
                .course(course)
                .name(courseUpdarionDto.getTechnology().getName())
                .photo(courseUpdarionDto.getTechnology().getPhoto())
                .build();
        technologyRepository.save(technology);
        List<PhotosCourse> photosCourses = new ArrayList<>();
        for (PhotosUpdateCourseDto photo : courseUpdarionDto.getPhotos()) {
            PhotosCourse photosCourse = PhotosCourse.builder()
                    .id(photo.getId())
                    .photo(photo.getPhoto())
                    .course(course)
                    .build();
            photosCourses.add(photosCourse);
        }
        photosRepository.saveAll(photosCourses);
        return courseRepository.save(course).getId();
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public void deleteCourse(UUID courseId) {
        Course course = findCourseById(courseId);
        course.setDelete(true);
        courseRepository.save(course);
        AdressCourse adressCourse = adressRepository.findAdressCourseByCourse(course);
        adressCourse.setDelete(true);
        adressRepository.save(adressCourse);
    }

    @Transactional
    public CourseResponseDto getCourses(UUID courseId) {
        Course course = findCourseById(courseId);
        CourseResponseDto courseResponseDto = courseResponseMapper.toCourseDto(courseRepository.findById(courseId).get());
        log.info("Data received from the database after mapping {}", courseResponseDto);
        AdressCourse adressCourse = adressRepository.findAdressCourseByCourse(course);
        log.info("адрес с базы данных {}", adressCourse);
        courseResponseDto.setAddress(addressResponseMapper.toDto(adressCourse));
        log.info("адрес после маппинга {}", courseResponseDto);
        courseResponseDto.setTechnology(technologyResponseMapper.toDto(technologyRepository.findTechnologiesByCourse(course)));
        log.info("Data received from the database after mapping {}", courseResponseDto);
        List<PhotosCourse> photosCourse = photosRepository.findAllByCourse(course);
        List<PhotosResponseDto> photosResponseDto = new ArrayList<>();
        for (PhotosCourse photo : photosCourse) {
            photosResponseDto.add(photosResponseMapper.toDto(photo));
        }
        courseResponseDto.setPhotos(photosResponseDto);
        log.info("Data received from the database after mapping {}", courseResponseDto);
        return courseResponseDto;
    }

    private Course findCourseById(UUID id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Course not found with id: " + id));
    }

    private Organizer findOrganizerById(UUID id) {
        return repositoryOrganizer.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Organizer not found with id: " + id));
    }
}
