package com.quathar.api.data.service.impl;

import com.quathar.api.data.entity.Author;
import com.quathar.api.data.entity.Poem;
import com.quathar.api.data.exception.ResourceNotFoundException;
import com.quathar.api.data.model.dto.PoemDTO;
import com.quathar.api.data.model.mapper.PoemMapper;
import com.quathar.api.data.repository.AuthorRepository;
import com.quathar.api.data.repository.PoemRepository;
import com.quathar.api.data.service.PoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h1>Poem Service Implementation</h1>
 *
 * @since 2023-06-09
 * @version 1.0
 * @author Q
 */
@Service
public class PoemServiceImpl extends GeneralServiceImpl<Poem, Long> implements PoemService {

    // <<-FIELDS->>
    private final PoemRepository   _poemRepository;
    private final AuthorRepository _authorRepository;

    // <<-CONSTRUCTOR->>
    @Autowired
    private PoemServiceImpl(
            PoemRepository   poemRepository,
            AuthorRepository authorRepository
    ) {
        super(poemRepository);
        _poemRepository   = poemRepository;
        _authorRepository = authorRepository;
    }

    // <<-METHODS->>
    @Override
    public Poem create(PoemDTO poemDTO) {
        Author author = null;

        Long authorId = poemDTO.getAuthor();
        if (authorId != null)
            author = _authorRepository.findById(authorId)
                                      .orElseThrow(ResourceNotFoundException::new);
        return _poemRepository.save(PoemMapper.toEntity(poemDTO, author));
    }

    @Override
    public Poem update(Long id, Poem updatedPoem) {
        Poem poem = _poemRepository.findById(id)
                                   .orElseThrow(ResourceNotFoundException::new);

        // Basics
        if (updatedPoem.getTitle() != null)
            poem.setTitle(updatedPoem.getTitle());
        if (updatedPoem.getTheme() != null)
            poem.setTheme(updatedPoem.getTheme());
        if (updatedPoem.getContent() != null)
            poem.setContent(updatedPoem.getContent());
        if (updatedPoem.getAuthor() != null)
            poem.setAuthor(updatedPoem.getAuthor());

        return _poemRepository.save(poem);
    }

    @Override
    public Poem update(Long id, PoemDTO updatedPoemDTO) {
        Poem poem = _poemRepository.findById(id)
                                   .orElseThrow(ResourceNotFoundException::new);

        // Basics
        if (updatedPoemDTO.getTitle() != null)
            poem.setTitle(updatedPoemDTO.getTitle());
        if (updatedPoemDTO.getTheme() != null)
            poem.setTheme(updatedPoemDTO.getTheme());
        if (updatedPoemDTO.getContent() != null)
            poem.setContent(updatedPoemDTO.getContent());

        // Relations
        if (updatedPoemDTO.getAuthor() != null) {
            Author author = _authorRepository.findById(updatedPoemDTO.getAuthor())
                                             .orElseThrow(ResourceNotFoundException::new);
            poem.setAuthor(author);
        }

        return _poemRepository.save(poem);
    }

}
