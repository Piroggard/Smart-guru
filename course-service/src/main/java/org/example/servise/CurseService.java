package org.example.servise;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.*;
import org.example.mapper.*;
import org.example.model.Course;
import org.example.model.Organizer;
import org.example.model.PhotosCourse;
import org.example.model.Technology;
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
    private final CourseMapperUpdate courseMapperUpdate;
    private final CourseResponseMapper courseResponseMapper;
    private final AddressMapper addressMapper;
    private final PhotosMapper photosMapper;
    private final TechnologyMapper technologyMapper;


    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public UUID createCourse(CourseCreationDTO courseCreationDTO) {
        log.info("Данные которые сохраняем в БД{}", courseCreationDTO);
        Course course = mapperCurseDB.toCourse(courseCreationDTO.getCourseRequestDto());
        log.info("Данные которые сохраняем в БД после маппинга {}", course);
        UUID idCourse = repositoryCourse.save(course).getId();

        AddressRequestDto addressRequestDto = courseCreationDTO.getAddressRequestDto();
        addressRequestDto.setCourseId(idCourse);
        repositoryAddress.save(addressMapper.toAdressCourse(addressRequestDto));

        List<PhotosCourse> photosCourses = new ArrayList<>();
        for (PhotosCourseDto photo : courseCreationDTO.getPhotos()) {
            photo.setCourseId(idCourse);
            photosCourses.add(photosMapper.toPhotosCourse(photo));
        }

        log.info("Данные фото которые сохраняем в БД {}", photosCourses);
        repositoryPhotos.saveAll(photosCourses);

        TechnologyRequestDto technologyRequestDto = courseCreationDTO.getTechnology();
        technologyRequestDto.setCourseId(idCourse);
        log.info("Данные технологии до маппинга {}", technologyRequestDto);
        Technology technology = technologyMapper.toTechnology(technologyRequestDto);
        log.info("Данные технологии которые сохраняем в БД {}", technology);
        repositoryTechnology.save(technology);
        return idCourse;
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public UUID updateCourse(CourseUpdarionDto courseUpdarionDto) {
        log.info("Данные которые обновляются в БД{}", courseUpdarionDto);
        Optional<Course> course1 = repositoryCourse.findById(courseUpdarionDto.getCourseUpdateDto().getId());
        Optional<Organizer> organizer = repositoryOrganizer.findById(course1.get().getOrganizerId().getId());
        log.info("Данные Организации  {}", organizer.get());
        Course course = Course.builder()
                .id(courseUpdarionDto.getCourseUpdateDto().getId())
                .name(courseUpdarionDto.getCourseUpdateDto().getName())
                .url(courseUpdarionDto.getCourseUpdateDto().getUrl())
                .type(courseUpdarionDto.getCourseUpdateDto().getType())
                .numberSeats(courseUpdarionDto.getCourseUpdateDto().getNumberSeats())
                .price(courseUpdarionDto.getCourseUpdateDto().getPrice())
                .photoProfile(courseUpdarionDto.getCourseUpdateDto().getPhotoProfile())
                .direction(courseUpdarionDto.getCourseUpdateDto().getDirection())
                .description(courseUpdarionDto.getCourseUpdateDto().getDescription())
                .duration(courseUpdarionDto.getCourseUpdateDto().getDuration())
                .status(courseUpdarionDto.getCourseUpdateDto().getStatus())
                .whatLearn(courseUpdarionDto.getCourseUpdateDto().getWhatLearn())
                .certificate(courseUpdarionDto.getCourseUpdateDto().getCertificate())
                .organizerId(organizer.get())
                .delete(courseUpdarionDto.getCourseUpdateDto().getDelete()).build();
        log.info("Данные которые сохраняем в БД после маппинга {}", course);
        return repositoryCourse.save(course).getId();
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public void deleteCourse(UUID courseId) {
        log.info("Метод deleteCourse {}", courseId);
        repositoryCourse.deleteById(courseId);
    }

    @Transactional
    public CourseResponseDto getCourses(UUID courseId) {
        log.info("Метод getCourses {}", courseId);
        CourseResponseDto courseResponseDto = courseResponseMapper.toCourseDto(repositoryCourse.findById(courseId).get());
        log.info("Данные которые получили с БД послек маппинга {}", courseResponseDto);
        return courseResponseDto;
    }
}
