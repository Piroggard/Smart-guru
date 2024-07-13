package org.example.contriller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.enam.TypeEnum;
import org.example.servise.TypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/type")
public class ControllerType {

    private final TypeService typeService;

    @GetMapping
    public List<TypeEnum> getType() {
        log.info("Метод getType");
        return typeService.getType();
    }
}
