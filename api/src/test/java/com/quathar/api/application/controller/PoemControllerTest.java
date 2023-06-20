package com.quathar.api.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.quathar.api.data.entity.Poem;
import com.quathar.api.data.service.PoemService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Q on 2023-06-20
 */
@SpringBootTest
@AutoConfigureMockMvc
public class PoemControllerTest {

    // <<-CONSTANTS->>
    private static final String AUTHORIZATION = "Authorization";

    // <<-FIELDS->>
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PoemService poemService;
    private boolean loginPerformed = false;
    private String authToken;

    // <<-METHODS->>
    @BeforeEach
    public void setUp() throws Exception {
        if (!loginPerformed) {
            // Perform login request
            String loginPayload = String.format(
                    "{\"username\":\"%s\",\"password\":\"%s\"}",
                    "Alpha",
                    "1234");
            mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(loginPayload))
                   .andExpect(MockMvcResultMatchers.status().isOk())
                   .andExpect(result -> {
                       String jsonResponse = result.getResponse().getContentAsString();
                       authToken = new ObjectMapper().readTree(jsonResponse)
                               .get("jwt")
                               .asText();
                    });
            loginPerformed = true;
        }
    }

    @Test
    public void getAllPoems_ReturnsListOfPoemDTOs() throws Exception {
        // Configure expected service behavior
        List<Poem> poemsForMock = new ArrayList<>();
        poemsForMock.add(new Poem());
        poemsForMock.add(new Poem());
        Mockito.when(poemService.getAll())
               .thenReturn(poemsForMock);

        // Perform simulated HTTP request
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/poetry/poems")
                        .header(AUTHORIZATION, "Bearer " + authToken))
               .andExpect(MockMvcResultMatchers.status().isOk());

        // Commentary explaining the purpose of the test
        // and any additional relevant information
    }

    @Test
    void getPoemById_ReturnsPoemDTO() throws Exception {
        Poem poemForMock = new Poem();
        poemForMock.setTitle  ("Test title");
        poemForMock.setContent("Test content");
        poemForMock.setTheme  ("Test theme");
        poemForMock.setAuthor (null);

        Long testId = 1L;
        Mockito.when(poemService.getById(testId))
               .thenReturn(poemForMock);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(String.format("/poetry/poems/%d", testId))
                        .header(AUTHORIZATION, "Bearer " + authToken))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

//    @Test
//    void getPoemById_ThrowsResourceNotFoundException() throws Exception {
//        Poem poemForMock = new Poem();
//        poemForMock.setTitle  ("Test title");
//        poemForMock.setContent("Test content");
//        poemForMock.setTheme  ("Test theme");
//        poemForMock.setAuthor (null);
//
//        Long testId = -1L;
//        Mockito.when(poemService.getById(testId))
//                .thenReturn(new ResourceNotFoundException());
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get(String.format("/poetry/poems/%d", testId))
//                        .header(AUTHORIZATION, "Bearer " + authToken))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }

}