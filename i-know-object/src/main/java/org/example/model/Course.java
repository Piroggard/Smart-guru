package org.example.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    // @Fetch(FetchMode.JOIN)
    List<Photos> photosCourse = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviews_id")
    //@Fetch(FetchMode.JOIN)
    List<Review> reviews = new ArrayList<>();

    public Course(String name, String url, String type, long numberSeats, long price, String photo, Address address, List<Photos> photosCourse, List<Review> reviews) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.numberSeats = numberSeats;
        this.price = price;
        this.photo = photo;
        this.address = address;
    }
}
