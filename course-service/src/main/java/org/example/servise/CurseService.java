package org.example.servise;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.*;
import org.example.mapper.*;
import org.example.model.*;
import org.example.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class CurseService {

    private final RepositoryCourse repositoryCourse;
    private final RepositoryAddress repositoryAddress;
    private final RepositoryPhotos repositoryPhotos;
    private final RepositoryTechnology repositoryTechnology;
    private final RepositoryOrganizer repositoryOrganizer;
    private final CourseMapper mapperCurseDB;
    private final CourseResponseMapper courseResponseMapper;
    private final AddressMapper addressMapper;
    private final PhotosMapper photosMapper;
    private final TechnologyMapper technologyMapper;
    private final TechnologyResponseMapper technologyResponseMapper;
    private final PhotosResponseMapper photosResponseMapper;
    private final AddressResponseMapper addressResponseMapper;

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public UUID createCourse(CourseCreationDto courseCreationDTO) {
        Course course = mapperCurseDB.toCourse(courseCreationDTO.getCourse());
        log.info("Data that we save in the database after mapping {}", course);
        UUID idCourse = repositoryCourse.save(course).getId();
        AddressRequestDto addressRequestDto = courseCreationDTO.getAddress();
        addressRequestDto.setCourseId(idCourse);
        repositoryAddress.save(addressMapper.toAdressCourse(addressRequestDto));
        List<PhotosCourse> photosCourses = new ArrayList<>();
        for (PhotosCourseDto photo : courseCreationDTO.getPhotos()) {
            photo.setCourseId(idCourse);
            photosCourses.add(photosMapper.toPhotosCourse(photo));
        }
        repositoryPhotos.saveAll(photosCourses);
        TechnologyRequestDto technologyRequestDto = courseCreationDTO.getTechnology();
        technologyRequestDto.setCourseId(idCourse);
        log.info("Data that we save in the database after mapping {}", technologyRequestDto);
        Technology technology = technologyMapper.toTechnology(technologyRequestDto);
        repositoryTechnology.save(technology);
        return idCourse;
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public UUID updateCourse(CourseUpdarionDto courseUpdarionDto) {
        Optional<Course> course1 = repositoryCourse.findById(courseUpdarionDto.getCourse().getId());
        Optional<Organizer> organizer = repositoryOrganizer.findById(course1.get().getOrganizerId().getId());
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
                .organizerId(organizer.get())
                .delete(courseUpdarionDto.getCourse().getDelete()).build();
        log.info("Data that we save in the database after mapping {}", course);
        AdressCourse adressCourse = AdressCourse.builder()
                .id(courseUpdarionDto.getAddress().getId())
                .courseId(courseUpdarionDto.getCourse().getId())
                .country(courseUpdarionDto.getAddress().getCountry())
                .city(courseUpdarionDto.getAddress().getCity())
                .street(courseUpdarionDto.getAddress().getStreet())
                .house(courseUpdarionDto.getAddress().getHouse())
                .district(courseUpdarionDto.getAddress().getDistrict())
                .delete(courseUpdarionDto.getAddress().getDelete())
                .build();
        repositoryAddress.save(adressCourse);
        Technology technology = Technology.builder()
                .id(courseUpdarionDto.getTechnology().getId())
                .name(courseUpdarionDto.getTechnology().getName())
                .photo(courseUpdarionDto.getTechnology().getPhoto())
                .courseId(courseUpdarionDto.getCourse().getId())
                .build();
        repositoryTechnology.save(technology);
        List<PhotosCourse> photosCourses = new ArrayList<>();
        for (PhotosUpdateCourseDto photo : courseUpdarionDto.getPhotos()) {
            PhotosCourse photosCourse = PhotosCourse.builder()
                    .id(photo.getId())
                    .courseId(courseUpdarionDto.getCourse().getId())
                    .photo(photo.getPhoto())
                    .build();
            photosCourses.add(photosCourse);
        }
        repositoryPhotos.saveAll(photosCourses);
        return repositoryCourse.save(course).getId();
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public void deleteCourse(UUID courseId) {
        repositoryCourse.deleteById(courseId);
    }

    @Transactional
    public CourseResponseDto getCourses(UUID courseId) {
        CourseResponseDto courseResponseDto = courseResponseMapper.toCourseDto(repositoryCourse.findById(courseId).get());
        courseResponseDto.setAddress(addressResponseMapper.toDto(repositoryAddress.findAdressCourseByCourseId(courseId)));
        courseResponseDto.setTechnology(technologyResponseMapper.toDto(repositoryTechnology.findTechnologiesByCourseId(courseId)));
        List<PhotosCourse> photosCourse = repositoryPhotos.findAllByCourseId(courseId);
        List<PhotosResponseDto> photosResponseDtos = new ArrayList<>();
        for (PhotosCourse course : photosCourse) {
            photosResponseDtos.add(photosResponseMapper.toDto(course));
        }
        courseResponseDto.setPhotos(photosResponseDtos);
        log.info("Data received from the database after mapping {}", courseResponseDto);
        return courseResponseDto;
    }
}
