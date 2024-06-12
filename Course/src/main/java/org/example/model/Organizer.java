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
@Table(name = "Organizer")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Organizer {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    UUID id;
    @Column(name = "name", nullable = false)
    String name;
    @Column(name = "email", nullable = false)
    String email;
    @Column(name = "password", nullable = false)
    String password;
    @Column(name = "role", nullable = false)
    String role;
    @Column(name = "date_create", nullable = false)
    LocalDateTime date_create; //Дата создания курса
    @Column(name = "date_update", nullable = false)
    LocalDateTime date_update;// Время обновления
    @Column(name = "detelete", nullable = false)
    Boolean detelete;
}
