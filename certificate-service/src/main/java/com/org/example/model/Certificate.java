package com.org.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "certificates")
@Builder
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "course_id", nullable = false)
    private UUID courseId;

    @Column(name = "organizer_id", nullable = false)
    private UUID organizerId;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "number", nullable = false, length = 128)
    private String number;

    @Column(name = "photo_certificate", nullable = false, length = 256)
    private String photoCertificate;

    @Column(name = "delete")
    private Boolean delete;

    @Column(name = "date_issuing")
    private LocalDate dateOfIssue;

    @Column(name = "date_create")
    private LocalDateTime dateOfCreate;

    @Column(name = "date_update")
    private LocalDateTime dateOfUpdate;
}
