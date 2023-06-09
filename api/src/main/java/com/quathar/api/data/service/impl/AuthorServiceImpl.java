package com.quathar.api.data.service.impl;

import com.quathar.api.data.entity.Author;
import com.quathar.api.data.model.dto.AuthorDTO;
import com.quathar.api.data.repository.AuthorRepository;
import com.quathar.api.data.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h1>Author Service Implementation</h1>
 *
 * @since 2023-06-09
 * @version 1.0
 * @author Q
 */
@Service
public class AuthorServiceImpl extends GeneralServiceImpl<Author, Long> implements AuthorService {

    // <<-FIELDS->>
    private final AuthorRepository _authorRepository;

    // <<-CONSTRUCTOR->>
    @Autowired
    private AuthorServiceImpl(AuthorRepository authorRepository) {
        super(authorRepository);
        _authorRepository = authorRepository;
    }

    // <<-METHODS->>
    @Override
    public Author create(AuthorDTO poemDTO) {
        return null;
    }

    @Override
    public Author update(Long id, Author updatedAuthor) {
        Author author = _authorRepository.findById(id)
                                         .orElseThrow(RuntimeException::new);

        // TODO: Implementation
        // TODO: Implementation
        // TODO: Implementation
        // TODO: Implementation

        return _authorRepository.save(author);
    }

    @Override
    public Author update(Long id, AuthorDTO updatedAuthorDTO) {
        return null;
    }
}
