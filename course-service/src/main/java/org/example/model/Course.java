package org.example.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.enam.DirectionEnum;
import org.example.enam.StatusEnum;
import org.example.enam.TypeEnum;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
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
@Table(name = "courses")
@Builder

public class Course {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "url", nullable = false)
    private String url;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "type")
    private TypeEnum type;

    @Column(name = "number_seats")
    private Long numberSeats;

    @Column(name = "price")
    private Long price;

    @Column(name = "photo_profile")
    private String photoProfile;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "direction")
    private DirectionEnum direction;

    @Column(name = "duration")
    private String duration;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEnum status;

    @Column(name = "what_learn")
    private String whatLearn;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "organizer_id", referencedColumnName = "id")
    private Organizer organizerId;

    @Column(name = "certificate")
    private Boolean certificate;

    @Column(name = "date_start_course")
    private LocalDateTime dateStartCourse;

    @Column(name = "date_finish_course")
    private LocalDateTime dateFinishCourse;

    @Column(name = "delete")
    private Boolean delete;

    @Column(name = "date_delete")
    private LocalDateTime dateDelete;

    @CreationTimestamp
    @Column(name = "date_create", updatable = false)
    private LocalDateTime dateCreate;

    @UpdateTimestamp
    @Column(name = "date_update")
    private LocalDateTime dateUpdate;
}
