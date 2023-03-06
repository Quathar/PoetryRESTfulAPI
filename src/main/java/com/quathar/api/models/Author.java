package com.quathar.api.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity(name="authors")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Author {

    // <<-FIELDS->>
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="author_id")
    private Long id;
    @Column
    private String name;
    @Column
    private LocalDate birthdate;
    @Column
    private String nationality;
    @OneToMany(mappedBy="author", orphanRemoval=true)
    @JsonManagedReference
    private List<Poem> poems;

}
