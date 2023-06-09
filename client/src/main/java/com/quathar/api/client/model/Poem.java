package com.quathar.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * <h1>Poem</h1>
 *
 * @since 2023-01-14
 * @version 1.0
 * @author Q
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@Data
public class Poem {

    // <<FIELDS->>
    private Long id;
    private Long author;
    private String theme;
    private String title;
    private String content;

    // <<-OVERRIDE->>
    @Override
    public String toString() {
        return String.format("{%n" +
                "\t\"id\" : %d,%n" +
                "\t\"author\" : %d,%n" +
                "\t\"theme\" : \"%s\",%n" +
                "\t\"title\" : \"%s\",%n" +
                "\t\"content\" : \"%s\"%n" +
                "}",
                id, author, theme, title, content);
    }

}
