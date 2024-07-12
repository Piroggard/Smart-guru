package org.example.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.enam.TypeEnum;
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
@Table(name = "type")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Type {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private TypeEnum name;
}
