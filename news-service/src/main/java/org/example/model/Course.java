package org.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.enam.DirectionEnum;
import org.example.enam.StatusEnum;
import org.example.enam.TypeEnum;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "url", nullable = false)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
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
    private boolean certificate;

    @Column(name = "date_start_course")
    private LocalDateTime dateStartCourse;

    @Column(name = "date_finish_course")
    private LocalDateTime dateFinishCourse;

    @Column(name = "delete")
    private boolean delete;

    @Column(name = "date_delete")
    private LocalDateTime dateDelete;

    @CreationTimestamp
    @Column(name = "date_create", updatable = false)
    private LocalDateTime dateCreate;

    @UpdateTimestamp
    @Column(name = "date_update")
    private LocalDateTime dateUpdate;
}
