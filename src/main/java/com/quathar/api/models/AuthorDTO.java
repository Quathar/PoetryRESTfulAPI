package com.quathar.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthorDTO {

    // <<-FIELDS->>
    private Long id;
    private String name;
    private LocalDate birthdate;
    private String nationality;
    private List<Long> poems;

    // <<-METHOD->>
    public static Author to(List<Poem> poems, AuthorDTO authorDTO) {
        Author author = new Author();

//        author.setName(authorDTO.getName());
//        author.setBirthdate(authorDTO.getBirthdate());
//        author.setNationality(authorDTO.getNationality());
//
//        // REVISAR como hacer
//        if (authorDTO.getPoems().size() > 0) {
//            List<Poem> poemsA = new ArrayList<>();
//            for (Long poem : authorDTO.getPoems()) {
//                poems
//            }
//            author.setPoems();
//        }

        return author;
    }

}
