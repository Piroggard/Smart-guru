package org.example.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
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
    UUID id;
    @Column(name = "country", nullable = false)
    String country;
    @Column(name = "city", nullable = false)
    String city;
    @Column(name = "street", nullable = false)
    String street;
    @Column(name = "house", nullable = false)
    String house;
    @Column(name = "district", nullable = false)
    String district;
}
