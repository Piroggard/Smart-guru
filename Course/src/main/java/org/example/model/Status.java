package org.example.model;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.enam.StatusEnum;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;

import java.util.UUID;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "status")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Status {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    UUID id;
    @Enumerated(EnumType.STRING)
    StatusEnum name;
}
