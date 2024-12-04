package com.felix.Book_Web_Service.service;

import com.felix.Book_Web_Service.model.Book;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class ManagementApiClient {

    private final String MANAGEMENT_API_URL = "http://localhost:8080/books";
    private final Client client = ClientBuilder.newClient();

    public List<Book> getAllBooks() {
        return Arrays.asList(client.target(MANAGEMENT_API_URL + "/getAllBooks")
                .request(MediaType.APPLICATION_JSON)
                .get(Book[].class));
    }

    public Book getBookById(Long id) {
        return client.target(MANAGEMENT_API_URL + "/getABook/" + id)
                .request(MediaType.APPLICATION_JSON)
                .get(Book.class);
    }

    public Response addBook(Book book) {
        return client.target(MANAGEMENT_API_URL + "/addBook")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(book, MediaType.APPLICATION_JSON));
    }

    public Response updateBook(Long id, Book book) {
        return client.target(MANAGEMENT_API_URL + "/updateBook/" + id)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(book, MediaType.APPLICATION_JSON));
    }

    public Response deleteBook(Long id) {
        return client.target(MANAGEMENT_API_URL + "/deleteBook/" + id)
                .request(MediaType.APPLICATION_JSON)
                .delete();
    }
}
