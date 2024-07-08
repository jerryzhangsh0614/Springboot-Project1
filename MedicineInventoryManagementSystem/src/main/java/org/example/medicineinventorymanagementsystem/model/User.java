package org.example.medicineinventorymanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.example.medicineinventorymanagementsystem.Enum.RoleTypeEnum;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Time;
import java.sql.Timestamp;

@Entity
public class User {
//    id: Integer, Primary Key
//    username: String
//    password: String, Hashed
//    email: String, Unique
//    role: Enum, Values: [USER, ADMIN]
//    created_at: Timestamp
//    updated_at: Timestamp
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private String username;

    @NotNull
    @Column(nullable = false)
    private String password;

    @Email
    @NotNull
    @NotEmpty
    @Size(max = 254) // Ensures the email does not exceed common email length limit
    @Pattern(
            regexp = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$",
            message = "Invalid email format"
    )
    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column
    @NotNull
    private RoleTypeEnum role;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;
}
