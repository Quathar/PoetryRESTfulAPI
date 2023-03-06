package com.quathar.api.repositories;

import com.quathar.api.models.Author;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends ListCrudRepository<Author, Long> {

    // NO-CONTENT

}
