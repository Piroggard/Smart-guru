package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

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
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsId;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(name = "direction", nullable = false) //Направление
    private String direction;

    @Column(name = "publication_date", nullable = false)
    private LocalDateTime publicationDate;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "heading", nullable = false)
    private String heading;

    @Column(name = "description", nullable = false)
    private String description;
}
