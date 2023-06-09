package com.quathar.api.data.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

/**
 * <h1>Author DTO (Data Transfer Object)</h1>
 *
 * @since 2023-01-10
 * @version 1.0
 * @author Q
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthorDTO {

    // <<-FIELDS->>
    private Long id;
    private String name;
    private LocalDate birthdate;
    private String nationality;
    private List<Long> poems;

}
