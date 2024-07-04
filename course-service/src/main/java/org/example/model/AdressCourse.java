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
@Table(name = "adress")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdressCourse {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "house", nullable = false)
    private String house;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "delete", nullable = false)
    private Boolean delete;

    @Column(name = "date_delete", nullable = false)
    private LocalDateTime dateDelete;

    @Column(name = "date_update", nullable = false)
    private LocalDateTime dateUpdate;

    @Column(name = "date_create", nullable = false)
    private LocalDateTime dateCreate;

}
