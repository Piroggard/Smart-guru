package org.example.servise;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CourseRequestDto;
import org.example.dto.DirectionDtoResponse;
import org.example.dto.StatusDtoResponse;
import org.example.mapper.MapperCurse;
import org.example.mapper.CourseMapper;
import org.example.mapper.MapperDirection;
import org.example.model.Direction;
import org.example.model.Status;
import org.example.repository.RepositoryCourse;
import org.example.repository.RepositoryDirection;
import org.example.repository.RepositoryStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class CurseService {

    private final RepositoryStatus repositoryStatus;
    private final RepositoryDirection repositoryDirection;
    private final RepositoryCourse repositoryCourse;
    private final MapperCurse mapperCurse;
    private final MapperDirection mapperDirection;
    private final CourseMapper mapperCurseDB;

    public List<StatusDtoResponse> getStatus() {
        List<Status> list = repositoryStatus.findAll();
        log.info("Получение данных из БД" + list);
        List<StatusDtoResponse> statusDTOResponseList = new ArrayList<>();
        for (Status status : list) {
            statusDTOResponseList.add(mapperCurse.toStatusDto(status));
            log.info("Данные после маппинга " + statusDTOResponseList);
        }
        return statusDTOResponseList;
    }

    public List<DirectionDtoResponse> getDirection() {
        List<Direction> list = repositoryDirection.findAll();
        log.info("Получение данных из БД" + list);
        List<DirectionDtoResponse> directionDtoResponses = new ArrayList<>();
        for (Direction direction : list) {
            directionDtoResponses.add(mapperDirection.toDirectionDTO(direction));
            log.info("Данные после маппинга " + directionDtoResponses);
        }
        return directionDtoResponses;
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public UUID createCourse(CourseRequestDto courseRequestDTO) {
        log.info("Данные которые сохраняем в БД" + courseRequestDTO);
        return repositoryCourse.save(mapperCurseDB.toCourseDTO(courseRequestDTO)).getId();
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public UUID updateCourse(CourseRequestDto courseRequestDTO) {
        log.info("Данные которые обновляются в БД" + courseRequestDTO);
        return repositoryCourse.save(mapperCurseDB.toCourseDTO(courseRequestDTO)).getId();
    }

    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public void deleteCourse(@PathVariable Enum courseId) {
            log.info("Метод deleteCourse " + courseId);
        repositoryCourse.deleteById(courseId);
    }
}
