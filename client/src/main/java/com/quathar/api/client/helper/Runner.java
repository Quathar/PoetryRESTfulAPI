package com.quathar.api.client.helper;

import com.quathar.api.client.model.JwtDTO;
import com.quathar.api.client.model.LoginDTO;
import com.quathar.api.client.model.Poem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <h1>Runner</h1>
 *
 * @since 2023-06-16
 * @version 1.0
 * @author Q
 */
@Component
public class Runner {

    // <<-CONSTANTS->>
    private static final String API_URL = "http://localhost:8080/poetry/poems";
    private static final String API_AUTH_URL = "http://localhost:8080/auth/login";
    private static final String BEARER = "Bearer";
    private static final String SYSTEM = "S Y S T E M:";
    private static final String ERROR = "E R R O R:";

    // <<-FIELD->>
    private RestTemplate _restTemplate;
    private String _jwt;

    // <<-METHODS->>
    @Bean
    public RestTemplate beanRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            _restTemplate = restTemplate;
            authenticate(br);
            application (br);
        }
        return args -> System.exit(0);
    }

    private void authenticate(BufferedReader br) throws IOException {
        boolean exit = false;
        while (!exit) {
            try {
                System.out.println("LOG IN: [username=Alpha, password=1234]\n");
                LoginDTO loginDTO = new LoginDTO();
                System.out.println("Username");
                loginDTO.setUsername(br.readLine());
                System.out.println("Password");
                loginDTO.setPassword(br.readLine());
                JwtDTO jwtDTO = _restTemplate.postForObject(API_AUTH_URL, loginDTO, JwtDTO.class);

                // If auth IS NOT successful it will throw RestClientException
                _jwt = jwtDTO.getJwt();
                exit = true;
            } catch (RestClientException ignored) {
                System.err.println(ERROR + " Wrong credentials");
            }
        }
    }

    private void application(BufferedReader br) throws IOException {
        boolean exit = false;
        while (!exit) {
            System.out.println("[ GET (-g) | POST (-p) | PUT (-u) | DELETE (-d) ] [ EXIT (-x) ]");
            switch (br.readLine().toLowerCase()) {
                case "-g" -> get   (br);
                case "-p" -> post  (br);
                case "-u" -> put   (br);
                case "-d" -> delete(br);
                case "-x" -> exit = true;
                default -> System.err.println(ERROR + " Wrong option");
            }
        }
        System.out.println(SYSTEM + " bye...");
    }

    // ===============
    // = = = GET = = =
    // ===============
    private boolean getAllPoems() {
        RequestEntity<Void> request = RequestEntity.get(API_URL)
                .header(HttpHeaders.AUTHORIZATION, String.format("%s %s", BEARER, _jwt))
                .build();

        Poem[] poems = _restTemplate.exchange(request, Poem[].class)
                                    .getBody();
        if (poems != null && poems.length > 0) {
            for (Poem poem : poems) System.out.println(poem);
            return true;
        } else {
            System.err.println(ERROR + " There is not poems yet");
            return false;
        }
    }

    private boolean getPoemByID(BufferedReader br) throws IOException {
        try {
            System.out.println("Enter the ID:");
            RequestEntity<Void> request = RequestEntity.get(String.format("%s/%s", API_URL, br.readLine()))
                    .header(HttpHeaders.AUTHORIZATION, String.format("%s %s", BEARER, _jwt))
                    .build();
            Poem poem = _restTemplate.exchange(request, Poem.class)
                                     .getBody();
            System.out.println(poem);
            return true;
        } catch (RestClientException e) {
            System.err.println(ERROR + " That ID doesn't exist");
            return false;
        }
    }

    private void get(BufferedReader br) throws IOException {
        boolean exit = false;
        while (!exit) {
            System.out.println("[ GET ALL (-all) | GET by ID (-id) ] [ EXIT (-x) ]");
            switch (br.readLine().toLowerCase()) {
                case "-all" -> exit = getAllPoems();
                case "-id"  -> exit = getPoemByID(br);
                case "-x"   -> exit = true;
                default     -> System.err.println(ERROR + " Wrong option");
            }
        }
    }

    // ================
    // = = = POST = = =
    // ================
    private Poem fillBody(BufferedReader br, String templateMessage) throws IOException {
        String input;
        Poem body = new Poem();

        // Requesting data
        System.out.printf(templateMessage, "THEME");
        input = br.readLine();
        if (!input.isBlank()) body.setTheme(input);

        System.out.printf(templateMessage, "TITLE");
        input = br.readLine();
        if (!input.isBlank()) body.setTitle(input);

        System.out.printf(templateMessage, "CONTENT");
        input = br.readLine();
        if (!input.isBlank()) body.setContent(input);

        return body;
    }

    private void post(BufferedReader br) throws IOException {
        String templateMessage = "'%s' [ Press enter for nothing ]%n";

        RequestEntity<Poem> request = RequestEntity.post(API_URL)
                .header(HttpHeaders.AUTHORIZATION, String.format("%s %s", BEARER, _jwt))
                .body(fillBody(br, templateMessage));
        System.out.println(_restTemplate.exchange(request, Poem.class)
                                        .getBody());
    }

    // ===============
    // = = = PUT = = =
    // ===============
    private void put(BufferedReader br) throws IOException {
        boolean exit = false;
        while (!exit) {
            System.out.println("Enter the ID: [ EXIT (-x) ]");
            String id = br.readLine();
            if (id.equalsIgnoreCase("-x"))
                break;
            try {
                // Checking if the ID is valid
                RequestEntity<Void> getRequest = RequestEntity.get(String.format("%s/%s", API_URL, id))
                        .header(HttpHeaders.AUTHORIZATION, String.format("%s %s", BEARER, _jwt))
                        .build();
                Poem poem = _restTemplate.exchange(getRequest, Poem.class) // throws RestClientException
                                         .getBody();

                // Updating the poem
                String template = "New '%s' [ Press enter to leave it unchanged ]%n";
                System.out.printf("B E F O R E%n%s%nabout to be updated. . .%n", poem);
                RequestEntity<Poem> putRequest = RequestEntity.put(String.format("%s/%s", API_URL, id))
                        .header(HttpHeaders.AUTHORIZATION, String.format("%s %s", BEARER, _jwt))
                        .body(fillBody(br, template));
                System.out.printf(
                        "A F T E R%n%s%n",
                        _restTemplate.exchange(putRequest, Poem.class).getBody()
                );
                System.out.println(SYSTEM + " Poem U P D A T E D");

                // Leaving
                exit = true;
            } catch (RestClientException e) {
                System.err.println(ERROR + " That ID doesn't exist");
            }
        }
    }

    // ==================
    // = = = DELETE = = =
    // ==================
    private void delete(BufferedReader br) throws IOException {
        boolean exit = false;
        while (!exit) {
            System.out.println("Enter the ID: [ EXIT (-x) ]");
            String id = br.readLine();
            if (id.equalsIgnoreCase("-x"))
                break;
            try {
                // Checking if the ID is valid
                RequestEntity<Void> getRequest = RequestEntity.get(String.format("%s/%s", API_URL, id))
                        .header(HttpHeaders.AUTHORIZATION, String.format("%s %s", BEARER, _jwt))
                        .build();
                Poem poem = _restTemplate.exchange(getRequest, Poem.class) // throws RestClientException
                                         .getBody();

                // Deleting the poem
                System.out.printf("%s%nabout to be deleted. . .%n", poem);
                RequestEntity<Void> deleteRequest = RequestEntity.delete(String.format("%s/%s", API_URL, id))
                        .header(HttpHeaders.AUTHORIZATION, String.format("%s %s", BEARER, _jwt))
                        .build();
                _restTemplate.exchange(deleteRequest, Poem.class);
                System.out.println(SYSTEM + " Poem D E L E T E D");

                // Leaving
                exit = true;
            } catch (RestClientException e) {
                System.err.println(ERROR + " That ID doesn't exist");
            }
        }
    }

}