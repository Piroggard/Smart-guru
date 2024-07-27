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
    private DirectionEnum direction;

    @Column(name = "image", length = 256)
    private String image;

    @Column(name = "title", length = 128, nullable = false)
    private String title;

    @Column(name = "description", length = 4096, nullable = false)
    private String description;

    @Column(name = "delete")
    private Boolean delete;

    @CreationTimestamp
    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @Column(name = "date_publication")
    private LocalDateTime datePublication;

    @UpdateTimestamp
    @Column(name = "date_update")
    private LocalDateTime dateUpdate;

    @Column(name = "date_delete")
    private LocalDateTime dateDelete;
}
