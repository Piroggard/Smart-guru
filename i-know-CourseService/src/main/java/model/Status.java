package model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;
    @Column(name = "name", nullable = false)
    StatusEnum name;
}
