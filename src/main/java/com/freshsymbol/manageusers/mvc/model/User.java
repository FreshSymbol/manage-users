package com.freshsymbol.manageusers.mvc.model;

import com.freshsymbol.manageusers.mvc.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 64, unique = true, nullable = false)
    private String email;
    @Column(length = 15, nullable = false)
    private String password;
    @Column(length = 55, nullable = false)
    private String name;
    @Column(length = 55, nullable = false)
    private String surname;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @CreationTimestamp
    private LocalDate dateOfCreate;
    private Boolean enabled;
}
