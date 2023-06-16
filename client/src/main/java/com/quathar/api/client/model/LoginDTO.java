package com.quathar.api.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <h1>Log In Data Transfer Object (DTO)</h1>
 *
 * @since 2023-06-16
 * @version 1.0
 * @author Q
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO {

    // <<-FIELDS->>
    private String username;
    private String password;

}
