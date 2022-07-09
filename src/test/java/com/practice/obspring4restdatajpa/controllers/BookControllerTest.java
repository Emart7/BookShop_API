package com.practice.obspring4restdatajpa.controllers;

import com.practice.obspring4restdatajpa.entities.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);

    }

    @DisplayName("Comprobar HelloWord desde controladores Spring REST")
    @Test
    void testController() {
        ResponseEntity<String> response =
                testRestTemplate.getForEntity("/testController", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());//Es lo mismo que la linea 38
        assertEquals("Hello World - test controller", response.getBody());
    }

    @Test
    void findAll() {
        ResponseEntity<Book[]> response =
            testRestTemplate.getForEntity("/api/books", Book[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());//Es lo mismo que la linea 49

        List<Book> books = Arrays.asList(Objects.requireNonNull(response.getBody()));
        System.out.println(books.size());
    }

    @Test
    void findById() {
        ResponseEntity<Book> response =
                testRestTemplate.getForEntity("//api/books/1", Book.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        String json =
//                "{" +
//                "\"title\":\"The Lord of the Rings\"," +
//                "\"author\":\"J.R.R. Tolkien\"," +
//                "\"pages\":284\"," +
//                "\"price\":29.99\"," +
//                "\"releaseDate\":2017-01-01\"," +
//                "\"onlineVersion\": false\"," +
//                "}";
      """
                { 
                    "title": "El ataque de las bestias - Desde Sprint Test",
                    "author": "Leon Cruzado",
                    "pages": 284,
                    "price": 29.99,
                    "releaseDate": "2017-01-01",
                    "onlineVersion": false
        }
        """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Book> response = testRestTemplate.exchange("/api/books", HttpMethod.POST, request, Book.class);

        Book result = response.getBody();

        assert result != null;
        assertEquals(1L, result.getId());
        assertEquals("El ataque de las bestias - Desde Sprint Test", result.getTitle());
//        assertEquals("The Lord of the Rings", result.getAuthor());
//        assertEquals(284, result.getPages());
//        assertEquals(29.99, result.getPrice());



    }
}