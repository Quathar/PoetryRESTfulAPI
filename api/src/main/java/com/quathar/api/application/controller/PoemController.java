package com.quathar.api.application.controller;

import com.quathar.api.data.entity.Poem;
import com.quathar.api.data.model.dto.PoemDTO;
import com.quathar.api.data.model.mapper.PoemMapper;
import com.quathar.api.data.service.PoemService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <h1>Author Controller</h1>
 *
 * @since 2023-01-09
 * @version 2.0
 * @author Q
 */
@RestController
@RequestMapping("/poetry/poems")
public class PoemController {

    // <<-FIELDS->>
    private final PoemService _poemService;

    // <<-CONSTRUCTOR->>
    @Autowired
    private PoemController(PoemService poemService) {
        _poemService = poemService;
    }

    // <<-METHODS->>
    @GetMapping("")
    public ResponseEntity<List<PoemDTO>> getAllPoems() {
        return ResponseEntity.ok(
                _poemService.getAll()
                            .stream()
                            .map(PoemMapper::toDto)
                            .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PoemDTO> getPoemById(@PathVariable(value="id") Long id) {
        return ResponseEntity.ok(PoemMapper.toDto(_poemService.getById(id)));
    }

    @PostMapping("")
    public ResponseEntity<PoemDTO> createPoem(@Valid @RequestBody PoemDTO poemDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(PoemMapper.toDto(_poemService.create(poemDTO)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PoemDTO> updatePoem(
            @PathVariable(value="id") Long id,
            @RequestBody PoemDTO updatedPoemDTO
    ) {
        return ResponseEntity.ok(PoemMapper.toDto(_poemService.update(id, updatedPoemDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePoem(@PathVariable(value="id") Long id) {
        _poemService.deleteById(id);
        return ResponseEntity.noContent()
                             .build();
    }

}
