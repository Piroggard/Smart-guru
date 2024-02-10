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
@Table(name = "photos_course")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Photos {

   public Photos(String photos, Long courseId) {
        this.photos = photos;
        this.courseId = courseId;
    }

    @Id
    @Column(name = "PHOTOS_COURSE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "photos", nullable = false)
    String photos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id") // Внешний ключ указывающий на курс
    private Long courseId;
   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id") // Убедитесь, что имя колонки соответствует имени в базе данных
    private Course course;*/
}
