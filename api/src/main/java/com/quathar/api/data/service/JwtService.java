package com.quathar.api.data.service;

import com.quathar.api.data.entity.User;
import io.jsonwebtoken.Claims;

/**
 * <h1>JSON Web Token (JWT) Service</h1>
 *
 * @since 2023-06-16
 * @version 1.0
 * @author Q
 */
public interface JwtService {

    /**
     * Creates a JSON Web Token (JWT) for the given user.
     *
     * @param user the user for whom to create the JWT
     * @return the generated JWT as a string
     */
    String createJwt(User user);

    /**
     * Extracts the claims from the given JSON Web Token (JWT).
     *
     * @param jwt the JSON web token
     * @return the extracted claims
     */
    Claims extractClaims(String jwt);

    /**
     * Extracts the user from the given JSON Web Token (JWT).
     *
     * @param jwt the JSON web token
     * @return the extracted user
     */
    User extractUser(String jwt);

}
