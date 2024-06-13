package org.example.contriller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.StatusDtoResponse;
import org.example.servise.CurseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/status")
public class ControllerStatus {
    private final CurseService curseService;
    @GetMapping
    public List<StatusDtoResponse> getStatus() {
        log.info("Метод getStatus");
        return curseService.getStatus();
    }
}
