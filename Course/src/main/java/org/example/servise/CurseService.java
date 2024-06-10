package org.example.servise;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.example.dto.StatusDTOResponse;
import org.example.mappers.MapperCurse;
import org.example.model.Status;
import org.example.repository.RepositoryStatus;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CurseService {
    private final RepositoryStatus repositoryStatus;
    private final MapperCurse mapperCurse;
    public List<StatusDTOResponse> getStatus (){
        List<Status> list = repositoryStatus.findAll();
        log.info("Получение данных из БД" + list);
        List<StatusDTOResponse> statusDTOResponseList = new ArrayList<>();
        for (Status status : list) {
            statusDTOResponseList.add(mapperCurse.toStatusDTO(status));
            log.info("Данные после маппинга " + statusDTOResponseList);
        }
        return statusDTOResponseList;
    }
}