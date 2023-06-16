package com.quathar.api.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h1>User</h1>
 *
 * @since 2023-06-16
 * @version 1.0
 * @author Q
 */
@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    // <<-FIELDS->>

    // Basics
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column
    private String username;
    @Column
    private String encryptedPassword;

}
