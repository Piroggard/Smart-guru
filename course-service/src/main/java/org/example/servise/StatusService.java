package org.example.servise;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.enam.StatusEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class StatusService {
    public List<StatusEnum> getStatus() {
        log.info("Метод getStatus");
        List<StatusEnum> statusEnumList = new ArrayList<>();
        for (StatusEnum day : StatusEnum.values()) {
            statusEnumList.add(day);
        }
        return statusEnumList;
    }
}
