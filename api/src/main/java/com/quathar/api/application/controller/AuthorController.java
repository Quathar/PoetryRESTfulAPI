package com.quathar.api.application.controller;

import com.quathar.api.data.entity.Author;
import com.quathar.api.data.model.dto.AuthorDTO;
import com.quathar.api.data.model.mapper.AuthorMapper;
import com.quathar.api.data.service.AuthorService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <h1>Author Controller</h1>
 *
 * @since 2023-01-09
 * @version 1.0
 * @author Q
 */
@RestController
@RequestMapping("/poetry/authors")
public class AuthorController {

    // <<-FIELDS->>
    private final AuthorService _authorService;

    // <<-CONSTRUCTOR->>
    @Autowired
    private AuthorController(AuthorService authorService) {
        _authorService = authorService;
    }

    // <<-METHODS->>
    @GetMapping("")
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        return ResponseEntity.ok(
                _authorService.getAll()
                              .stream()
                              .map(AuthorMapper::toDto)
                              .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable(value="id") Long id) {
        return ResponseEntity.ok(AuthorMapper.toDto(_authorService.getById(id)));
    }

    @PostMapping("")
    public ResponseEntity<Author> createPoem(@Valid @RequestBody AuthorDTO authorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(_authorService.create(authorDTO));
    }

    // TODO: Check this
    // TODO: Check this
    // TODO: Check this
    @PutMapping("/{id}")
    public ResponseEntity<Author> updatePoem(
            @PathVariable(value="id") Long id,
            @RequestBody AuthorDTO updatedAuthorDTO
    ) {
        return ResponseEntity.ok(_authorService.update(id, updatedAuthorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> deletePoem(@PathVariable(value="id") Long id) {
        _authorService.deleteById(id);
        return ResponseEntity.noContent()
                             .build();
    }

}
