package org.example.model;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "photos_course")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Photos {

    @Id
    @Column(name = "PHOTOS_COURSE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "photos", nullable = false)
    String photos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public Photos(String photos, Course course) {
        this.photos = photos;
        this.course = course;
    }
}