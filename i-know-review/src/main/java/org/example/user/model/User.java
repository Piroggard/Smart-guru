package org.example.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.course.model.CompletedCourse;

import javax.persistence.*;
import java.util.Set;

@Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(toBuilder = true)
    @Entity
    @Table(name = "users")
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "name")
        private String name;
        @Column(name = "email")
        private String email;
        @OneToMany(mappedBy = "course")
        private Set<CompletedCourse> completedCourses;

}
