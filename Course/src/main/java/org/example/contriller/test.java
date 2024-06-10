package org.example.contriller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@Slf4j
@AllArgsConstructor
@RestController
public class test {


    @GetMapping()
    public String test() {
        log.info("Метод getStatus");
        return "OK";
    }

}
