package com.org.example.dto;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CertificateDto {
    private UUID id;
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
