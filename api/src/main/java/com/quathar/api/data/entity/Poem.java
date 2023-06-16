package com.quathar.api.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <h1>Poem</h1>
 *
 * @since 2023-01-09
 * @version 1.0
 * @author Q
 */
@Entity(name="poems")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Poem {

    // <<-FIELDS->>

    // Basics
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="poem_id")
    private Long id;
    @Column                 // Amor, Gozo, Melancolía, Muerte
    private String theme;   // Odio, Valentía, Venganza, Vida
    @Column
    private String title;
    @Column(length = 300)
    private String content;

    // Relations
    @ManyToOne
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "FK_author"))
    // It only works in the @ManyToOne relationship if it's UNIDIRECTIONAL,
    // this is BIDIRECTIONAL.
//    @OnDelete(action=OnDeleteAction.CASCADE)
    @JsonBackReference
    private Author author;

}
