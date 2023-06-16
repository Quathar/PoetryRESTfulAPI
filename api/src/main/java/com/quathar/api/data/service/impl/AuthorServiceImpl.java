package com.quathar.api.data.service.impl;

import com.quathar.api.data.entity.Author;
import com.quathar.api.data.entity.Poem;
import com.quathar.api.data.exception.ResourceNotFoundException;
import com.quathar.api.data.model.dto.AuthorDTO;
import com.quathar.api.data.repository.AuthorRepository;
import com.quathar.api.data.repository.PoemRepository;
import com.quathar.api.data.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private final PoemRepository _poemRepository;

    // <<-CONSTRUCTOR->>
    @Autowired
    private AuthorServiceImpl(
            AuthorRepository authorRepository,
            PoemRepository   poemRepository
    ) {
        super(authorRepository);
        _authorRepository = authorRepository;
        _poemRepository   = poemRepository;
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

        if (updatedAuthor.getName() != null)
            author.setName(updatedAuthor.getName());
        if (updatedAuthor.getBirthdate() != null)
            author.setBirthdate(updatedAuthor.getBirthdate());
        if (updatedAuthor.getNationality() != null)
            author.setNationality(updatedAuthor.getNationality());
//        if (updatedAuthor.getPoems() != null)
//            author.setPoems(updatedAuthor.getPoems());

        return _authorRepository.save(author);
    }

    @Override
    public Author update(Long id, AuthorDTO updatedAuthorDTO) {
        Author author = _authorRepository.findById(id)
                                       .orElseThrow(ResourceNotFoundException::new);

        // Basics
        if (updatedAuthorDTO.getName() != null)
            author.setName(updatedAuthorDTO.getName());
        if (updatedAuthorDTO.getBirthdate() != null)
            author.setBirthdate(updatedAuthorDTO.getBirthdate());
        if (updatedAuthorDTO.getNationality() != null)
            author.setNationality(updatedAuthorDTO.getNationality());

        // Relations
//        List<Long> poemIds = updatedAuthorDTO.getPoems();
//        if (poemIds != null && poemIds.size() > 0) {
//            List<Poem> poems = poemIds.stream()
//                                      .map(poemId ->
//                                              _poemRepository.findById(poemId)
//                                                             .orElseThrow(ResourceNotFoundException::new))
//                                      .collect(Collectors.toList());
//
//            author.setPoems(poems);
//        }
        return _authorRepository.save(author);
    }

}