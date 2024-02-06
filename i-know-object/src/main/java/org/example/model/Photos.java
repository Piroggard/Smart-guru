package org.example.model;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@Table(name = "reviews")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Photos {
    @Id
    @Column(name = "photos_course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "photos", nullable = false)
    String photos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id") // Убедитесь, что имя колонки соответствует имени в базе данных
    private Course course;
}
