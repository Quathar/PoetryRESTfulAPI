package com.quathar.api.controllers;

import com.quathar.api.models.Author;
import jakarta.validation.Valid;

import com.quathar.api.models.AuthorDTO;
import com.quathar.api.models.Poem;
import com.quathar.api.repositories.AuthorRepository;
import com.quathar.api.repositories.PoemRepository;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/poetry")
public class AuthorController {

    // <<-FIELDS->>
    private final AuthorRepository _authorsRepository;
    private final PoemRepository _poemsRepository;

    // <<-CONSTRUCTOR->>
    @Autowired
    private AuthorController(AuthorRepository authorsRepository, PoemRepository poemsRepository) {
        _authorsRepository = authorsRepository;
        _poemsRepository = poemsRepository;
    }

    // <<-METHODS->>
    // <<-GET->>
    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return _authorsRepository.findAll();
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable(value="id") Long id) {
        return ResponseEntity.of(_authorsRepository.findById(id));
    }

    // <<-POST->>
    @PostMapping("/authors")
    public ResponseEntity<Author> createPoem(@Valid @RequestBody AuthorDTO authorDTO) {
        // Inserting data
        Author author = new Author();

        author.setName(authorDTO.getName());
        author.setBirthdate(authorDTO.getBirthdate());
        author.setNationality(authorDTO.getNationality());

        Optional<Poem> poem;
        if (authorDTO.getPoems() != null) {
            // The list is not necessary for functionality
            // it's a visual element to return the poems that belong to the author in the body
            List<Poem> poems = new ArrayList<>();
            for (Long poem_id : authorDTO.getPoems()) {
                poem = _poemsRepository.findById(poem_id);
                if (poem.isEmpty())
                    return ResponseEntity.badRequest().build();
                poem.get().setAuthor(author);
                poems.add(poem.get());
            }
            author.setPoems(poems);
        }

        // Creating the new Author
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(_authorsRepository.save(author));
    }

    // <<-PUT->>
    @PutMapping("/authors/{id}")
    public ResponseEntity<Author> updatePoem(
            @PathVariable(value="id") Long id,
            @RequestBody AuthorDTO authorDTODetails
    ) {
        // Checking if the author exists
        Optional<Author> author = _authorsRepository.findById(id);
        if (author.isEmpty())
            return ResponseEntity.notFound().build();

        // Updating data
        if (authorDTODetails.getName() != null)
            author.get().setName(authorDTODetails.getName());
        if (authorDTODetails.getBirthdate() != null)
            author.get().setBirthdate(authorDTODetails.getBirthdate());
        if (authorDTODetails.getNationality() != null)
            author.get().setNationality(authorDTODetails.getNationality());

        Optional<Poem> poem;
        if (authorDTODetails.getPoems() != null)
            for (Long poem_id : authorDTODetails.getPoems()) {
                poem = _poemsRepository.findById(poem_id);
                if (poem.isEmpty())
                    return ResponseEntity.badRequest().build();
                poem.get().setAuthor(author.get());
            }

        // Updating the new Author
        return ResponseEntity.ok(_authorsRepository.save(author.get()));
    }

    // <<-DELETE->>
    @DeleteMapping("/authors/{id}")
    public ResponseEntity<Author> deletePoem(@PathVariable(value="id") Long id) {
        // Checking if the author exists
        Optional<Author> author = _authorsRepository.findById(id);
        if (author.isEmpty())
            return ResponseEntity.notFound().build();

        // Deleting the author
        _authorsRepository.deleteById(author.get().getId());
        return ResponseEntity.noContent().build();
    }

}
