package org.example.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "address_courses")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdressCourse {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "course_id", nullable = false)
    private UUID courseId;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = true)
    private String street;

    @Column(name = "house", nullable = true)
    private String house;

    @Column(name = "district", nullable = true)
    private String district;

    @Column(name = "delete", nullable = true)
    private Boolean delete;

    @Column(name = "date_delete", nullable = true)
    private LocalDateTime dateDelete;

    @LastModifiedDate
    @Column(name = "date_update", nullable = true)
    private LocalDateTime dateUpdate;

    @CreatedDate
    @Column(name = "date_create", nullable = true, updatable = false)
    private LocalDateTime dateCreate;

}
