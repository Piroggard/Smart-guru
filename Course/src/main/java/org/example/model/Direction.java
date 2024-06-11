package org.example.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.enam.DirectionEnum;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "direction")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Direction {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    UUID id;
    @Enumerated(EnumType.STRING)
    DirectionEnum name;

}
