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
public class AdressCourse {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private Course course;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private String house;

    @Column(name = "district")
    private String district;

    @Column(name = "delete")
    private Boolean delete;

    @Column(name = "date_delete")
    private LocalDateTime dateDelete;

    @LastModifiedDate
    @Column(name = "date_update")
    private LocalDateTime dateUpdate;

    @CreatedDate
    @Column(name = "date_create", updatable = false)
    private LocalDateTime dateCreate;
}
