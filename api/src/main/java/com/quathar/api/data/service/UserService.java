package com.quathar.api.data.service;

import com.quathar.api.data.entity.User;

/**
 * <h1>User Service</h1>
 *
 * @since 2023-06-16
 * @version 1.0
 * @author Q
 */
public interface UserService extends GeneralService<User, Long> {

    User getByUsername(String username);

}
