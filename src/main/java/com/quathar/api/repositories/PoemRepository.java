package com.quathar.api.repositories;

import com.quathar.api.models.Poem;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoemRepository extends ListCrudRepository<Poem, Long> {

    // NO-CONTENT

}
