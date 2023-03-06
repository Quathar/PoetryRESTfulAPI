package com.quathar.api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="poems")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Poem {

    // Solo funciona en la relación @ManyToOne si esta es Unidireccional, esta es Bidireccional
//    @OnDelete(action=OnDeleteAction.CASCADE)

    // <<-FIELDS->>
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="poem_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name="author_id")
    @JsonBackReference
    private Author author;
    @Column                 // Amor, Gozo, Melancolía, Muerte
    private String theme;   // Odio, Valentía, Venganza, Vida
    @Column
    private String title;
    @Column
    private String content;

}
