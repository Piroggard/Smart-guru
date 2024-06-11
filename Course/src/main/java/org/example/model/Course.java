package org.example.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Type", referencedColumnName = "id")
    //@Column(name = "type", nullable = false)
    Type type; //Тип курса
    @Column(name = "number_seats", nullable = false)
    Long number_seats; // количество мест
    @Column(name = "price", nullable = false)
    Long price; //Цена курса
    @Column(name = "photo_profile", nullable = false)
    String photo_profile; // фото профиля
    @Column(name = "description", nullable = false)
    String description; // Описание курса
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direction", referencedColumnName = "id")
    //@Column(name = "direction_id", nullable = false)
    Direction direction_id; // Направление
    @Column(name = "duration", nullable = false)
    String duration; // продолжительность
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Organizer", referencedColumnName = "id")
    //@Column(name = "organizer_id", nullable = false)
    Organizer organizer_id; // ID организации
    @Column(name = "certificate", nullable = false)
    Boolean certificate; //Сертификат
    @Column(name = "date_create", nullable = false)
    LocalDateTime date_create; //Дата создания курса
    @Column(name = "date_start_course", nullable = false)
    LocalDateTime date_start_course; //Дата начала курса
    @Column(name = "date_finish_course", nullable = false)
    LocalDateTime date_finish_course; //Дата окончания курса
    @Column(name = "delete", nullable = false)
    Boolean delete; //Признак удаленности
    @Column(name = "date_delete", nullable = false)
    LocalDateTime date_delete;// Время удаления
    @Column(name = "date_update", nullable = false)
    LocalDateTime date_update;// Время обновления
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Status", referencedColumnName = "id")
    //@Column(name = "status_id", nullable = false)
    Status status_id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address", referencedColumnName = "id")
    //@Column(name = "adress_id", nullable = false)
    AdressCourse adress_id;
}
