package com.quathar.api.controllers;

import jakarta.validation.Valid;
import com.quathar.api.models.Poem;
import com.quathar.api.models.PoemDTO;
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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/poetry")
public class PoemController {

    // <<-FIELDS->>
    private final PoemRepository _poemsRepository;
    private final AuthorRepository _authorsRepository;

    // <<-CONSTRUCTOR->>
    @Autowired
    private PoemController(PoemRepository poemsRepository, AuthorRepository authorsRepository) {
        _poemsRepository = poemsRepository;
        _authorsRepository = authorsRepository;
    }

    // <<-METHODS->>
    // <<-GET->>
    @GetMapping("/poems")
    public List<PoemDTO> getAllPoems() {
        return _poemsRepository.findAll().stream().map(PoemDTO::from).toList();
    }

    @GetMapping("/poems/{id}")
    public ResponseEntity<PoemDTO> getPoemById(@PathVariable(value="id") Long id) {
        // Search for the poem by the ID
        // if it's not found returns 'NOT FOUND'
        // otherwise returns the requested poemDTO
        return _poemsRepository
                .findById(id)
                .map(poem -> ResponseEntity.of(
                        Optional.of(PoemDTO.from(poem))
                ))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    // <<-POST->> // =========================REVISAR
    @PostMapping("/poems")
    public ResponseEntity<Poem> createPoem(@Valid @RequestBody PoemDTO poemDTO) {
        // Try to create the new poem, if the given author ID is null or doesn't exist returns a 'BAD REQUEST'
        if (poemDTO.getAuthor() == null)
            return ResponseEntity.badRequest().build();

        // To save a poem an author is needed
        // Search for the author by the ID
        // if it's not found then returns 'BAD REQUEST'
        // otherwise save the new poem
        return _authorsRepository
                .findById(poemDTO.getAuthor())
                .map(value -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(_poemsRepository.save(PoemDTO.to(poemDTO, value))))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    // <<-PUT->>
    @PutMapping("/poems/{id}")
    public ResponseEntity<Poem> updatePoem(
            @PathVariable(value="id") Long id,
            @RequestBody PoemDTO poemDTODetails
    ) {
        // Checking if the poem exists
        Optional<Poem> poem = _poemsRepository.findById(id);
        if (poem.isEmpty())
            return ResponseEntity.notFound().build();

        // Updating the data
        _authorsRepository
                .findById(poemDTODetails.getAuthor())
                .ifPresent(value -> poem.get().setAuthor(value));

        if (poemDTODetails.getTheme() != null)
            poem.get().setTheme(poemDTODetails.getTheme());
        if (poemDTODetails.getTitle() != null)
            poem.get().setTitle(poemDTODetails.getTitle());
        if (poemDTODetails.getContent() != null)
            poem.get().setContent(poemDTODetails.getContent());

        return ResponseEntity.ok(_poemsRepository.save(poem.get()));
    }

    // <<-DELETE->>
    @DeleteMapping("/poems/{id}")
    public ResponseEntity<Object> deletePoem(@PathVariable(value="id") Long id) {
        // Checking if the poem exists
        Optional<Poem> poem = _poemsRepository.findById(id);
        if (poem.isEmpty())
            return ResponseEntity.notFound().build();

        // Deleting the poem
        _poemsRepository.deleteById(poem.get().getId());
        return ResponseEntity.noContent().build();
    }

}
