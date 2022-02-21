package com.example.demo;

import com.example.demo.dto.BookDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookIT {
	@Autowired
	private ObjectMapper objectMapper;

	@LocalServerPort
	void setPort(int port) {
		RestAssured.port = port;
	}

	@BeforeEach
	public void clear(){
		RestAssured.get("/book/clear");
	}


	@Test
	public void shouldAddBook() throws Exception {
		BookDto book = BookDto.builder()
				.author("author2")
				.isbn("isbn2")
				.title("title2")
				.build();
		List<BookDto> books = List.of(book);
		final String jsonRequest = objectMapper.writeValueAsString(book);
		List<BookDto> bookList = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.body(jsonRequest)
				.when()
				.post("/book/save")
				.then()
				.statusCode(200)
				.extract()
				.body()
				.jsonPath()
				.getList("$", BookDto.class);
		assertEquals(bookList, books);
	}

	@Test
	public void shouldFindBook() throws Exception {
		BookDto book = BookDto.builder()
				.author("author2")
				.isbn("isbn2")
				.title("title2")
				.build();
		List<BookDto> books = List.of(book);
		String jsonRequest = objectMapper.writeValueAsString(book);
		RestAssured
				.given()
				.contentType(ContentType.JSON)
				.body(jsonRequest)
				.when()
				.post("/book/save");
		List<BookDto> bookList = RestAssured.given()
				.param("searchField","au")
				.when().get("/book/find")
				.then()
				.statusCode(200)
				.extract()
				.body()
				.jsonPath()
				.getList("$", BookDto.class);
		assertEquals(bookList, books);
	}
}
