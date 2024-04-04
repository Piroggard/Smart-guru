package org.example.user.model;

import lombok.*;

import javax.persistence.*;

@Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(toBuilder = true)
    @Entity
    @Table(name = "users")
    @EqualsAndHashCode(exclude = {"id"})
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "name")
        private String name;
        @Column(name = "email")
        private String email;

}
