package com.quathar.api.data.service.impl;

import com.quathar.api.data.entity.User;
import com.quathar.api.data.exception.ResourceNotFoundException;
import com.quathar.api.data.repository.UserRepository;
import com.quathar.api.data.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <h1>User Service Implementation</h1>
 *
 * @since 2023-06-16
 * @version 1.0
 * @author Q
 */
@Service
public class UserServiceImpl extends GeneralServiceImpl<User, Long> implements UserService {

    // <<-FIELD->>
    private UserRepository _userRepository;

    // <<-CONSTRUCTOR->>
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        _userRepository = userRepository;
    }

    // <<-METHOD->>
    @Override
    public User getByUsername(String username) {
        return _userRepository.findByUsername(username)
                              .orElseThrow(ResourceNotFoundException::new);
    }

}