package com.quathar.api.data.model.mapper;

import com.quathar.api.data.entity.Author;
import com.quathar.api.data.entity.Poem;
import com.quathar.api.data.model.dto.AuthorDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Author Mapper</h1>
 *
 * @since 2023-06-09
 * @version 1.0
 * @author Q
 */
public class AuthorMapper {

    // <<-METHODS->>
    public static Author toEntity(AuthorDTO authorDTO, List<Poem> poems) {
        Author author = new Author();

        // Basics
        author.setId         (authorDTO.getId());
        author.setName       (authorDTO.getName());
        author.setBirthdate  (authorDTO.getBirthdate());
        author.setNationality(authorDTO.getNationality());

        // Relations
        author.setPoems(poems);

        return author;
    }

    public static AuthorDTO toDto(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();

        // Basics
        authorDTO.setId         (author.getId());
        authorDTO.setName       (author.getName());
        authorDTO.setBirthdate  (author.getBirthdate());
        authorDTO.setNationality(author.getNationality());

        // Relations
        List<Poem> poems = author.getPoems();
        if (poems != null && poems.size() > 0) {
            List<Long> poemsIds = new ArrayList<>();
            for (Poem poem : poems)
                poemsIds.add(poem.getId());
            authorDTO.setPoems(poemsIds);
        }

        return authorDTO;
    }


}
