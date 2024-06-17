package com.org.example.controller;

import com.org.example.service.CertificatesServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ api/v1/certificate" +
        "\n")
@Slf4j
@AllArgsConstructor
@RestController
public class CertificatesController {
    private final CertificatesServiceImpl certificateService;

}
