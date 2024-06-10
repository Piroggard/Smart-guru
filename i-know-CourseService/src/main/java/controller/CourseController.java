package controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Status;
import org.springframework.web.bind.annotation.*;
import servise.CurseService;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping
public class CourseController {
    private final CurseService curseService;

    @GetMapping("/status")
    public List<Status> getStatus() {
        log.info("Метод getStatus");
        return curseService.getStatus();
    }

    @GetMapping()
    public String test() {
        log.info("Метод getStatus");
        return "OK";
    }
}