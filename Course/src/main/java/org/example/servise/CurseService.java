package org.example.servise;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.example.dto.CourseRequestDTO;
import org.example.dto.CourseResponseDTO;
import org.example.dto.DirectionDTOResponse;
import org.example.dto.StatusDTOResponse;
import org.example.enam.StatusEnum;
import org.example.mappers.MapperCurse;
import org.example.mappers.MapperCurseDB;
import org.example.mappers.MapperDirection;
import org.example.model.Course;
import org.example.model.Direction;
import org.example.model.Status;
import org.example.repository.RepositoryCourse;
import org.example.repository.RepositoryDirection;
import org.example.repository.RepositoryStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CurseService {
    private final RepositoryStatus repositoryStatus;
    private final RepositoryDirection repositoryDirection;
    private final RepositoryCourse repositoryCourse;
    private final MapperCurse mapperCurse;
    private final MapperDirection mapperDirection;
    private final MapperCurseDB mapperCurseDB;

    public List<StatusDTOResponse> getStatus() {
        List<Status> list = repositoryStatus.findAll();
        log.info("Получение данных из БД" + list);
        List<StatusDTOResponse> statusDTOResponseList = new ArrayList<>();
        for (Status status : list) {
            statusDTOResponseList.add(mapperCurse.toStatusDTO(status));
            log.info("Данные после маппинга " + statusDTOResponseList);
        }
        return statusDTOResponseList;
    }

    public List<DirectionDTOResponse> getDirection() {
        List<Direction> list = repositoryDirection.findAll();
        log.info("Получение данных из БД" + list);
        List<DirectionDTOResponse> directionDTOResponses = new ArrayList<>();
        for (Direction direction : list) {
            directionDTOResponses.add(mapperDirection.toDirectionDTO(direction));
            log.info("Данные после маппинга " + directionDTOResponses);
        }
        return directionDTOResponses;

    }
    @Transactional(timeout = 30, rollbackFor = Exception.class)
    public Course createCourse (CourseRequestDTO courseRequestDTO){
        log.info("Данные которые сохраняем в БД" + courseRequestDTO);
       return repositoryCourse.save(mapperCurseDB.toCourseDTO(courseRequestDTO));
    }
}
