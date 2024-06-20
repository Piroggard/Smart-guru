package com.org.example.dto;


import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@ToString
public class CertificateDto {
    private UUID certificateId;
    private UUID userId;
    private  UUID courseId;
    private String name;
    private String number;
    private String photoCertificate;
    private Boolean delete;
    private LocalDate dateOfIssue;
    private LocalDateTime dateOfCreate;
    private LocalDateTime dateOfUpdate;
}
