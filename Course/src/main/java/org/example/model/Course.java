package org.example.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "course")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Course {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    UUID id;
    @Column(name = "name", nullable = false)
    String name; // Название курса
    @Column(name = "url", nullable = false)
    String url; // не обязательное поле
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Type", referencedColumnName = "id")
    Type type; //Тип курса
    @Column(name = "number_seats", nullable = false)
    Long numberSeats; // количество мест
    @Column(name = "price", nullable = false)
    Long price; //Цена курса
    @Column(name = "photoProfile", nullable = false)
    String photoProfile; // фото профиля
    @Column(name = "description", nullable = false)
    String description; // Описание курса
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "directionId", referencedColumnName = "id")
    Direction directionId; // Направление
    @Column(name = "duration", nullable = false)
    String duration; // продолжительность
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "organizer_id", referencedColumnName = "id")
    Organizer organizerId; // ID организации
    @Column(name = "certificate", nullable = false)
    Boolean certificate; //Сертификат
    @Column(name = "date_create", nullable = false)
    LocalDateTime dateCreate; //Дата создания курса
    @Column(name = "date_start_course", nullable = false)
    LocalDateTime dateStartCourse; //Дата начала курса
    @Column(name = "date_finish_course", nullable = false)
    LocalDateTime dateFinishCourse; //Дата окончания курса
    @Column(name = "delete", nullable = false)
    Boolean delete; //Признак удаленности
    @Column(name = "date_delete", nullable = false)
    LocalDateTime dateDelete;// Время удаления
    @Column(name = "date_update", nullable = false)
    LocalDateTime dateUpdate;// Время обновления
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    Status statusId;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "adress_id", referencedColumnName = "id")
    AdressCourse addressId;
}
