package org.example.servise;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.enam.DirectionEnum;
import org.example.enam.StatusEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@AllArgsConstructor
@Service
public class DirectionService {

    public List<DirectionEnum> getDirection() {
        log.info("Метод getDirection");
        List<DirectionEnum> directionEnums = new ArrayList<>();
        for (DirectionEnum directionEnum : DirectionEnum.values()) {
            directionEnums.add(directionEnum);
        }
        return directionEnums;
    }
}
