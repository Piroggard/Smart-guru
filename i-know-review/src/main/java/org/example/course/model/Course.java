package org.example.course.model;

import lombok.*;
import org.example.user.model.User;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(toBuilder = true)
    @Entity
    @Table(name = "courses")
    @EqualsAndHashCode(exclude = {"id"})
    public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @ManyToMany
    @JoinTable(
            name = "completed_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> studentsWhoCompleted;

}
