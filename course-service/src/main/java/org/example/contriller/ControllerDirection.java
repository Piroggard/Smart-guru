package org.example.contriller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.enam.DirectionEnum;
import org.example.servise.DirectionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/direction")
public class ControllerDirection {
    private final DirectionService directionService;
    @GetMapping
    public List<DirectionEnum> getDirection() {
        log.info("Метод getDirection");
        return directionService.getDirection();
    }
}
