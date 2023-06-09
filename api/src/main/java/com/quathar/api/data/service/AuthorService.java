package com.quathar.api.data.service;

import com.quathar.api.data.entity.Author;
import com.quathar.api.data.model.dto.AuthorDTO;

/**
 * <h1>Author Service</h1>
 *
 * @since 2023-06-09
 * @version 1.0
 * @author Q
 */
public interface AuthorService extends GeneralService<Author, Long> {

    Author create(AuthorDTO poemDTO);
    Author update(Long id, AuthorDTO updatedAuthorDTO);
}
