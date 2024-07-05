package org.example.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.enam.DirectionEnum;
import org.example.enam.StatusEnum;
import org.example.enam.TypeEnum;
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
@Table(name = "courses")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Course {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name; // Название курса

    @Column(name = "url", nullable = false)
    private String url; // не обязательное поле

    @JoinColumn(name = "type", nullable = false)
    private TypeEnum type; //Тип курса

    @Column(name = "number_seats", nullable = false)
    private Long numberSeats; // количество мест

    @Column(name = "price", nullable = false)
    private Long price; //Цена курса

    @Column(name = "photo_profile", nullable = true)
    private String photoProfile; // фото профиля

    @Column(name = "description", nullable = false)
    private String description; // Описание курса

    @Column(name = "direction", nullable = false)
    private DirectionEnum direction; // направление

    @Column(name = "duration", nullable = false)
    private String duration; // продолжительность

    @Column(name = "status", nullable = false)
    private StatusEnum status; // статус

    @Column(name = "what_learn", nullable = false)
    private String whatLearn; // Чему научусь

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "organizer_id", referencedColumnName = "id")
    private Organizer organizerId; // ID организации

    @Column(name = "certificate", nullable = true)
    private Boolean certificate; //Сертификат

    @Column(name = "date_start_course", nullable = true)
    private LocalDateTime dateStartCourse; //Дата начала курса

    @Column(name = "date_finish_course", nullable = true)
    private LocalDateTime dateFinishCourse; //Дата окончания курса

    @Column(name = "delete", nullable = true)
    private Boolean delete; //Признак удаленности

    @Column(name = "date_delete", nullable = true)
    private LocalDateTime dateDelete;// Время удаления

    @CreatedDate
    @Column(name = "date_create", nullable = true, updatable = false)
    private LocalDateTime dateCreate; //Дата создания курса\

    @LastModifiedDate
    @Column(name = "date_update", nullable = true)
    private LocalDateTime dateUpdate;// Время обновления

}
