package com.org.example.controller;

import com.org.example.dto.CertificateDto;
import com.org.example.service.CertificatesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/certificate")
@Slf4j
@AllArgsConstructor
@RestController
public class CertificateController {
    private final CertificatesService certificateService;

    @GetMapping("{/id}")
    public CertificateDto findById(@PathVariable("id") UUID id) {
        log.info("Getting review by id " + id);
        return certificateService.getCertificate(id);
    }

    @PostMapping
    public CertificateDto create(@RequestBody CertificateDto certificateDto) {
        log.info("Creating certificate " + certificateDto);
        return certificateService.addCertificate(certificateDto);
    }

    @PatchMapping("{/id}")
    public CertificateDto update(@RequestBody CertificateDto certificateDto, @PathVariable("id") UUID id) {
        log.info("Updating certificate " + certificateDto);
        return certificateService.updateCertificate(certificateDto);
    }

    @DeleteMapping("{/id}")
    public void delete(@PathVariable("id") UUID id) {
        log.info("Deleting certificate " + id);
        certificateService.deleteCertificate(id);
    }


}
