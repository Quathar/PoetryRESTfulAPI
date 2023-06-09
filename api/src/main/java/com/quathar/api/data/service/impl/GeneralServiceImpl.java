package com.quathar.api.data.service.impl;

import com.quathar.api.data.exception.ResourceNotFoundException;
import com.quathar.api.data.service.GeneralService;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

/**
 * <h1>General Service Implementation</h1>
 *
 * @param <T> the entity type
 * @param <ID> the ID type of the entity
 *
 * @since 2023-06-09
 * @version 1.0
 * @author Q
 */
public class GeneralServiceImpl<T, ID> implements GeneralService<T, ID> {

    // <<-FIELDS->>
    private final ListCrudRepository<T, ID> _generalRepository;

    // <<-CONSTRUCTOR->>
    public GeneralServiceImpl(ListCrudRepository<T, ID> generalRepository) {
        _generalRepository = generalRepository;
    }

    // <<-METHODS->>
    @Override
    public List<T> getAll() {
        return _generalRepository.findAll();
    }

    @Override
    public T getById(ID id) {
        return _generalRepository.findById(id)
                                 .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public T create(T entity) {
        return _generalRepository.save(entity);
    }

    @Override
    public T update(ID id, T updatedEntity) {
        return null;
    }

    @Override
    public void deleteAll() {
        _generalRepository.deleteAll();
    }

    @Override
    public void deleteById(ID id) {
        _generalRepository.deleteById(id);
    }

}
