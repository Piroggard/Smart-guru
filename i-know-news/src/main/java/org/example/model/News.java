package org.example.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "news")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class News {
    @Id
    @Column(name = "news_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long newsId;

    @Column(name = "course_id", nullable = false)
    Long courseId;

    @Column(name = "direction", nullable = false) //Направление
    String direction;

    @Column(name = "publication_date", nullable = false)
    LocalDateTime publicationDate;

    @Column(name = "image", nullable = false)
    String image;

    @Column(name = "heading", nullable = false)
    String heading;

    @Column(name = "description", nullable = false)
    String description;
}
