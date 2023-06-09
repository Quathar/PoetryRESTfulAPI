package com.quathar.api.data.service;

import java.util.List;

/**
 * <h1>General Service</h1>
 *
 * @since 2023-06-09
 * @version 1.0
 * @author Q
 */
public interface GeneralService<T, ID> {

    /**
     * Retrieves all entities.
     *
     * @return a list of all entities
     */
    List<T> getAll();

    /**
     * Retrieves an entity by its ID.
     *
     * @param id the ID of the entity
     * @return the entity with the specified ID
     */
    T getById(ID id);

    /**
     *
     * @param entity
     * @return
     */
    T create(T entity);

    /**
     * Updates an existing entity with the specified ID.
     *
     * @param id the ID of the entity to update
     * @param updatedEntity the updated entity
     * @return the updated entity
     */
    T update(ID id, T updatedEntity);

    /**
     * Deletes all entities.
     */
    void deleteAll();

    /**
     * Deletes an entity with the specified ID.
     *
     * @param id the ID of the entity to delete
     */
    void deleteById(ID id);

}
