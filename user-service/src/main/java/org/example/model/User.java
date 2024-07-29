package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.model.enums.Role;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "firstname", length = 128, nullable = false)
    private String firstname;

    @Column(name = "lastname", length = 128, nullable = false)
    private String lastname;

    @Column(name = "middle_name", length = 128)
    private String middleName;

    @Column(name = "email", length = 128, unique = true, nullable = false)
    private String email;

    @Column(name = "photo", length = 256)
    private String photo;

    @Column(name = "password", length = 1024, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 128)
    private Role role;

    @Column(name = "delete")
    private boolean delete;

    @CreationTimestamp
    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @UpdateTimestamp
    @Column(name = "date_update")
    private LocalDateTime dateUpdate;

    @Column(name = "date_delete")
    private LocalDateTime dateDelete;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
