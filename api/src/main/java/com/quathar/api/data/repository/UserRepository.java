package com.quathar.api.data.repository;

import com.quathar.api.data.entity.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * <h1>User Repository</h1>
 *
 * @since 2023-06-16
 * @version 1.0
 * @author Q
 */
@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
