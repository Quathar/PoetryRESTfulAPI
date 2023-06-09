package com.quathar.api.data.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>Poem DTO (Data Transfer Object)</h1>
 *
 * @since 2023-01-10
 * @version 1.0
 * @author Q
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PoemDTO {

    // <<-FIELDS->>
    private Long id;
    private Long author;
    private String theme;
    private String title;
    private String content;

}
