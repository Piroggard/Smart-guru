package org.example.servise;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.*;
import org.example.mapper.*;
import org.example.model.Course;
import org.example.model.PhotosCourse;
import org.example.repository.RepositoryAddress;
import org.example.repository.RepositoryCourse;
import org.example.repository.RepositoryPhotos;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class CurseService {
    private final RepositoryCourse repositoryCourse;
    private final RepositoryAddress repositoryAddress;
    private final RepositoryPhotos repositoryPhotos;
    private final CourseMapper mapperCurseDB;
    private final CourseMapperUpdate courseMapperUpdate;
    private final CourseResponseMapper courseResponseMapper;
    private final AddressMapper addressMapper;
    private final PhotosMapper photosMapper;


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
        return idCourse;
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public UUID updateCourse(CourseRequestUpdateDto courseRequestUpdateDto) {
        log.info("Данные которые обновляются в БД{}", courseRequestUpdateDto);
        Course course = courseMapperUpdate.toCourse(courseRequestUpdateDto);
        log.info("Данные которые сохраняем в БД после маппинга {}", course);
        return repositoryCourse.save(courseMapperUpdate.toCourse(courseRequestUpdateDto)).getId();
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public void deleteCourse(UUID courseId) {
        log.info("Метод deleteCourse {}", courseId);
        repositoryCourse.deleteById(courseId);
    }

    @Transactional
    public CourseResponseDto getCourses (UUID courseId){
        log.info("Метод getCourses {}", courseId);
        CourseResponseDto courseResponseDto = courseResponseMapper.toCourseDto(repositoryCourse.findById(courseId).get());
        log.info("Данные которые получили с БД послек маппинга {}", courseResponseDto);
        return courseResponseDto;
    }
}
