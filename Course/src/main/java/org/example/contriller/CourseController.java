package org.example.contriller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.StatusDTOResponse;
import org.example.model.Status;
import org.example.servise.CurseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping
public class CourseController {
    private final CurseService curseService;

    @GetMapping("/status")
    public List<StatusDTOResponse> getStatus() {
        log.info("Метод getStatus");
        return curseService.getStatus();
    }

    @GetMapping()
    public String test() {
        log.info("Метод getStatus");
        return "OK";
    }
}