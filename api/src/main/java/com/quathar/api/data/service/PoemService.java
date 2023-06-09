package com.quathar.api.data.service;

import com.quathar.api.data.entity.Poem;
import com.quathar.api.data.model.dto.PoemDTO;

/**
 * <h1>Poem Service</h1>
 *
 * @since 2023-06-09
 * @version 1.0
 * @author Q
 */
public interface PoemService extends GeneralService<Poem, Long> {

    Poem create(PoemDTO poemDTO);
    Poem update(Long id, PoemDTO updatedPoemDTO);

}
