package com.quathar.api.data.repository;

import com.quathar.api.data.entity.Author;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * <h1>Author Repository</h1>
 *
 * @since 2023-01-09
 * @version 1.0
 * @author Q
 */
@Repository
public interface AuthorRepository extends ListCrudRepository<Author, Long> {

    // NO-CONTENT
    // NO-CONTENT
    // NO-CONTENT

}
