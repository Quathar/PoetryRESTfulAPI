package com.quathar.api.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <h1>Authentication Data Transfer Object (DTO)</h1>
 *
 * @since 2023-06-16
 * @version 1.0
 * @author Q
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuthenticationDTO {

    // <<-FIELDS->>
    private String username;
    private String password;

}