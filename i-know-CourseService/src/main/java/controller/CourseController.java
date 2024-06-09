package controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Status;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import servise.CurseService;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController { // Исправлено название класса
    private final CurseService curseService; // Исправлено название класса

    @GetMapping("/status")
    public List<Status> getStatus() {
        log.info("Метод getStatus");
        return curseService.getStatus(); // Исправлено название метода
    }
}