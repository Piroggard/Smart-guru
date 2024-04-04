package org.example.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "url", nullable = false)
    String url;

    @Column(name = "type", nullable = false)
    String type;

    @Column(name = "number_seats", nullable = false)
    long numberSeats;

    @Column(name = "price", nullable = false)
    long price;

    @Column(name = "photo_profile", nullable = false)
    String photo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @Fetch(FetchMode.JOIN)
    Address address;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    List<Photos> photosCourse;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    List<Reviews> reviews;

}