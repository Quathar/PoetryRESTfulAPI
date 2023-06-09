package com.quathar.api.data.model.mapper;

import com.quathar.api.data.entity.Author;
import com.quathar.api.data.entity.Poem;
import com.quathar.api.data.model.dto.PoemDTO;

/**
 * <h1>Poem Mapper</h1>
 *
 * @since 2023-06-09
 * @version 1.0
 * @author Q
 */
public class PoemMapper {

    // <<-METHODS->>
    public static PoemDTO toDto(Poem poem) {
        PoemDTO poemDTO = new PoemDTO();

        if (poem.getAuthor() != null)
            poemDTO.setAuthor(poem.getAuthor().getId());
        poemDTO.setId(poem.getId());
        poemDTO.setTheme(poem.getTheme());
        poemDTO.setTitle(poem.getTitle());
        poemDTO.setContent(poem.getContent());

        return poemDTO;
    }

    public static Poem toEntity(PoemDTO poemDTO, Author author) {
        Poem poem = new Poem();

        poem.setTheme  (poemDTO.getTheme());
        poem.setTitle  (poemDTO.getTitle());
        poem.setContent(poemDTO.getContent());
        poem.setAuthor(author);

        return poem;
    }

}
