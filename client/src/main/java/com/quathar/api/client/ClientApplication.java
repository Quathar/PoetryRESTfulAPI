package com.quathar.api.client;

import com.quathar.api.client.model.Poem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <h1>Client Application</h1>
 *
 * @since 2023-01-14
 * @version 2.0
 * @author Q
 */
@SpringBootApplication
public class ClientApplication {

    // <<-CONSTANT->>
    private static final String API_URL = "http://localhost:8080/poetry/poems";
    private static final String SYSTEM = "S Y S T E M:";
    private static final String ERROR = "E R R O R:";

    // <<-MAIN METHOD->>
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    // <<-METHODS->>
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    // TODO: Check PUT (FAIL)
    // TODO: Check PUT (FAIL)
    // TODO: Check PUT (FAIL)
    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            boolean exit = false;
            while (!exit) {
                System.out.println("[ GET (-g) | POST (-p) | PUT (-u) | DELETE (-d) ] [ EXIT -x ]");
                switch (br.readLine().toLowerCase()) {
                    case "-g" -> get   (br, restTemplate);
                    case "-p" -> post  (br, restTemplate);
                    case "-u" -> put   (br, restTemplate);
                    case "-d" -> delete(br, restTemplate);
                    case "-x" -> exit = true;
                    default   -> System.err.println(ERROR + " Wrong option");
                }
            }
        }
        System.out.println(SYSTEM + " bye...");
        return args -> System.exit(0);
    }

    // ===============
    // = = = GET = = =
    // ===============
    private void getAllPoems(RestTemplate restTemplate) {
        Poem[] poems = restTemplate.getForObject(API_URL, Poem[].class);
        if (poems != null && poems.length > 0)
            for (Poem poem : poems) System.out.println(poem);
        else System.err.println(ERROR + " There is not poems yet");
    }

    private void getPoemByID(BufferedReader br, RestTemplate restTemplate) throws IOException {
        System.out.println("Enter the ID:");
        try {
            Poem poem = restTemplate.getForObject(  // throws RestClientException
                    String.format("%s/%s", API_URL, br.readLine()),
                    Poem.class
            );
            System.out.println(poem);
        } catch (RestClientException e) {
            System.err.println(ERROR + " That ID doesn't exist");
        }
    }

    private void get(BufferedReader br, RestTemplate restTemplate) throws IOException {
        boolean exit = false;
        while (!exit) {
            System.out.println("[ GET ALL (-all) | GET by ID (-id) ] [ EXIT (-x) ]");
            switch (br.readLine().toLowerCase()) {
                case "-all" -> {
                    getAllPoems(restTemplate);
                    exit = true;
                }
                case "-id" -> {
                    getPoemByID(br, restTemplate);
                    exit = true;
                }
                case "-x" -> exit = true;
                default   -> System.err.println(ERROR + " Wrong option");
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

    private void post(BufferedReader br, RestTemplate restTemplate) throws IOException {
        String templateMessage = "'%s' [ Press enter for nothing ]%n";
        Poem body = fillBody(br, templateMessage);
        restTemplate.postForObject(API_URL, body, Poem.class);
        System.out.println(body);
    }

    // ===============
    // = = = PUT = = =
    // ===============
    private void put(BufferedReader br, RestTemplate restTemplate) throws IOException {
        boolean exit = false;
        while (!exit) {
            System.out.println("Enter the ID: [ EXIT (-x) ]");
            String id = br.readLine();
            if (id.equalsIgnoreCase("-x"))
                break;
            try {
                // Checking if the ID is valid
                Poem poem = restTemplate.getForObject(  // throws RestClientException
                        String.format("%s/%s", API_URL, id),
                        Poem.class
                );

                // Requesting data
                String template = "New '%s' [ Press enter to leave it unchanged ]%n";
                Poem body = fillBody(br, template);

                // Updating the poem
                System.out.printf("B E F O R E%n%s%nabout to be updated. . .%n", poem);
                restTemplate.put(String.format("%s/%s", API_URL, id), body);
                System.out.printf("A F T E R%n%s%n",
                        restTemplate.getForObject(
                            String.format("%s/%s", API_URL, id),
                            Poem.class
                        )
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
    private void delete(BufferedReader br, RestTemplate restTemplate) throws IOException {
        boolean exit = false;
        while (!exit) {
            System.out.println("Enter the ID: [ EXIT (-x) ]");
            String input = br.readLine();
            if (input.equalsIgnoreCase("-x"))
                break;
            try {
                // Checking if the ID is valid
                Poem poem = restTemplate.getForObject(  // throws RestClientException
                        String.format("%s/%s", API_URL, input),
                        Poem.class
                );

                // Deleting the poem
                System.out.printf("%s%nabout to be deleted. . .%n", poem);
                restTemplate.delete(String.format("%s/%s", API_URL, input));
                System.out.println(SYSTEM + " Poem D E L E T E D");

                // Leaving
                exit = true;
            } catch (RestClientException e) {
                System.err.println(ERROR + " That ID doesn't exist");
            }
        }
    }

}
