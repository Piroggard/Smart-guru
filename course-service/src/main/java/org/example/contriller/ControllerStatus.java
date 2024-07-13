package org.example.contriller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.enam.StatusEnum;
import org.example.servise.StatusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/status")
public class ControllerStatus {

    private final StatusService statusService;

    @GetMapping
    public List<StatusEnum> getStatus() {
        log.info("Received a request for a list of Status");
        return statusService.getStatus();
    }
}
