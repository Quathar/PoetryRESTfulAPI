package com.quathar.api.data.repository;

import com.quathar.api.data.entity.Poem;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * <h1>Poem Repository</h1>
 *
 * @since 2023-01-09
 * @version 1.0
 * @author Q
 */
@Repository
public interface PoemRepository extends ListCrudRepository<Poem, Long> {

    // NO-CONTENT
    // NO-CONTENT
    // NO-CONTENT

}
