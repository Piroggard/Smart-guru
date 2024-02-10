package org.example.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@Table(name = "reviews")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Review {

    public Review(String name, String description, Long courseId) {
        this.name = name;
        this.description = description;
        this.courseId = courseId;
    }

    @Id
    @Column(name = "reviews_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "description", nullable = false)
    String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id") // Внешний ключ указывающий на курс
    private Long courseId;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id") // Убедитесь, что имя колонки соответствует имени в базе данных
    private Course course;*/

}
