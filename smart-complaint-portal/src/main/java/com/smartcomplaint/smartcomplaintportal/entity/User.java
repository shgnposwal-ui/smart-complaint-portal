package com.smartcomplaint.smartcomplaintportal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 10)
    private String phoneNumber;
    private String address;

    private String city;

    private String state;

    private String pincode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder.Default
    private Boolean isActive = true;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "citizen")
    @Builder.Default
    private List<Complaint> complaints = new ArrayList<>();

    @OneToMany(mappedBy = "assignedOfficer")
    @Builder.Default
    private List<Complaint> assignedComplaints = new ArrayList<>();


}