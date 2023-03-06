package com.quathar.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PoemDTO {

    // <<-FIELDS->>
    private Long id;
    private Long author;
    private String theme;
    private String title;
    private String content;

    // <<-METHODS->>
    public static PoemDTO from(Poem poem) {
        PoemDTO poemDTO = new PoemDTO();

        if (poem.getAuthor() != null)
            poemDTO.setAuthor(poem.getAuthor().getId());
        poemDTO.setId(poem.getId());
        poemDTO.setTheme(poem.getTheme());
        poemDTO.setTitle(poem.getTitle());
        poemDTO.setContent(poem.getContent());

        return poemDTO;
    }

    public static Poem to(PoemDTO poemDTO, Author author) {
        Poem poem = new Poem();

        poem.setAuthor(author);
        poem.setTheme(poemDTO.getTheme());
        poem.setTitle(poemDTO.getTitle());
        poem.setContent(poemDTO.getContent());

        return poem;
    }

}
