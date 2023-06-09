package com.quathar.api.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;
import java.util.List;

/**
 * <h1>Author</h1>
 *
 * @since 2023-01-09
 * @version 1.0
 * @author Q
 */
@Entity(name="authors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Author {

    // <<-FIELDS->>

    // Basics
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

    // Relations
    @OneToMany(mappedBy="author", orphanRemoval=true)
    @ToString.Exclude
    @JsonManagedReference
    private List<Poem> poems;

}
