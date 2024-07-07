package org.example.servise;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CourseRequestDto;
import org.example.mapper.CourseMapper;
import org.example.model.Course;
import org.example.repository.RepositoryCourse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class CurseService {
    private final RepositoryCourse repositoryCourse;
    private final CourseMapper mapperCurseDB;


    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public UUID createCourse(CourseRequestDto courseRequestDTO) {
        log.info("Данные которые сохраняем в БД{}", courseRequestDTO);
        try {
            Course course = mapperCurseDB.toCourse(courseRequestDTO);
            log.info("Данные которые сохраняем в БД после маппинга {}", course);
        } catch (Exception e){
            log.error(e.getMessage());
        }

        return repositoryCourse.save(mapperCurseDB.toCourse(courseRequestDTO)).getId();
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public UUID updateCourse(CourseRequestDto courseRequestDTO) {
        log.info("Данные которые обновляются в БД{}", courseRequestDTO);
        return repositoryCourse.save(mapperCurseDB.toCourse(courseRequestDTO)).getId();
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public void deleteCourse(@PathVariable Enum courseId) {
        log.info("Метод deleteCourse {}", courseId);
        repositoryCourse.deleteById(courseId);
    }
}
