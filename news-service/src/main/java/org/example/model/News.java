package org.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.enam.DirectionEnum;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder

@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @Enumerated(EnumType.STRING)
    @Column(name = "direction", length = 128, nullable = false)
    private DirectionEnum direction; // направление

    @Column(name = "image", length = 256)
    private String image; //изображение

    @Column(name = "title", length = 128, nullable = false)
    private String title; //заголовок

    @Column(name = "description", length = 4096, nullable = false)
    private String description; //описание

    @Column(name = "delete")
    private Boolean delete; //признак удаленности

    @CreationTimestamp
    @Column(name = "date_create")
    private LocalDateTime dateCreate; //время создания

    @Column(name = "date_publication")
    private LocalDateTime datePublication; //время публикации

    @UpdateTimestamp
    @Column(name = "date_update")
    private LocalDateTime dateUpdate; //время обновления

    @Column(name = "date_delete")
    private LocalDateTime dateDelete; //время удаления
}
