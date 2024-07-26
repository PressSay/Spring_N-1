package com.example.accessingdata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.example.accessingdata.models.Address;
import com.example.accessingdata.models.Author;
import com.example.accessingdata.models.Book;
import com.example.accessingdata.models.GroupAuthor;
import com.example.accessingdata.models.Library;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class AccessingdataApplicationTests {

	// @Autowired
	private RestTemplate template;

	AccessingdataApplicationTests() {
		this.template = new RestTemplate();
	}
	// private static RestTemplate template = new RestTemplate();

	private static String BOOK_ENDPOINT = "http://localhost:8080/books";
	private static String BOOK_RESOURCE_ENDPOINT = "http://localhost:8080/resource/books";
	private static String AUTHOR_ENDPOINT = "http://localhost:8080/authors";
	private static String AUTHOR_RESOURCE_ENDPOINT = "http://localhost:8080/resource/authors";
	private static String ADDRESS_ENDPOINT = "http://localhost:8080/addresses";
	private static String ADDRESS_RESOURCE_ENDPOINT = "http://localhost:8080/resource/addresses";
	private static String LIBRARY_ENDPOINT = "http://localhost:8080/libraries";
	private static String LIBRARY_RESOURCE_ENDPOINT = "http://localhost:8080/resource/libraries";
	private static String GROUP_ENDPOINT = "http://localhost:8080/groupAuthors";
	private static String GROUP_RESOURCE_ENDPOINT = "http://localhost:8080/resource/groupAuthors";

	private static String LIBRARY_NAME = "My Library";
	private static String AUTHOR_NAME = "George Orwell";

	@Test
	public void whenSaveOneToOneRelationship_thenCorrect() {
		Library library = new Library(LIBRARY_NAME);
		ResponseEntity<Library> resLibrary = template.postForEntity(LIBRARY_ENDPOINT,
				library, Library.class);

		Address address = new Address("Main street, nr 1");
		template.postForEntity(ADDRESS_ENDPOINT, address, Address.class);

		// String libraryAddressUrl = "libraryAddress";
		String libraryAddressUrl = "address";

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Content-type", "text/uri-list");
		HttpEntity<String> httpEntity = new HttpEntity<>(ADDRESS_ENDPOINT + "/1",
				requestHeaders);
		template.exchange(LIBRARY_ENDPOINT + "/1/" + libraryAddressUrl,
				HttpMethod.PUT, httpEntity, String.class);

		assertEquals(true, resLibrary.hasBody(), "resLibrary no body in whenSaveOneToOneRelationship_thenCorrect");

		String requestBody = LIBRARY_RESOURCE_ENDPOINT + "/" + 1;
		var resLibraryTest = template.getForEntity(requestBody, Library.class);
		assertEquals(true, resLibraryTest.getStatusCode().is2xxSuccessful(), "failed in get library with id 1");
	}

	@Test
	public void whenSaveOneToManyRelationship_thenCorrect() {
		Library library = new Library(LIBRARY_NAME + " (new)");
		ResponseEntity<Library> resLibrary = template.postForEntity(LIBRARY_ENDPOINT,
				library, Library.class);
		assertEquals(true, resLibrary.getStatusCode().is2xxSuccessful(), "resLibrary failed");

		Book book1 = new Book("Dune");
		template.postForEntity(BOOK_ENDPOINT, book1, Book.class);

		Book book2 = new Book("1984");
		template.postForEntity(BOOK_ENDPOINT, book2, Book.class);

		var resListBook = template.getForEntity(BOOK_RESOURCE_ENDPOINT, Book[].class);

		assertEquals(true, resListBook.hasBody(), "resListBook failed");

		String url1 = BOOK_ENDPOINT + "/" + resListBook.getBody()[0].getId() + "/library";
		String url2 = BOOK_ENDPOINT + "/" + resListBook.getBody()[1].getId() + "/library";

		var resLibraryTest = template.getForEntity(LIBRARY_RESOURCE_ENDPOINT, Library[].class);
		assertEquals(true, resLibraryTest.getStatusCode().is2xxSuccessful(), "failed in get library with id 1");

		List<Library> libraries = (List<Library>) Arrays.asList(resLibraryTest.getBody());
		String requestBody = LIBRARY_ENDPOINT + "/" + libraries.get(0).getId();

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Content-type", "text/uri-list");
		HttpEntity<String> httpEntity = new HttpEntity<>(requestBody,
				requestHeaders);
		ResponseEntity<Void> resbook1 = template.exchange(url1,
				HttpMethod.PUT, httpEntity, Void.class);
		ResponseEntity<Void> resbook2 = template.exchange(url2,
				HttpMethod.PUT, httpEntity, Void.class);

		assertEquals(true, resbook1.getStatusCode().is2xxSuccessful(), "update book1 incorrect");
		assertEquals(true, resbook2.getStatusCode().is2xxSuccessful(), "update book1 incorrect");
	}

	@Test
	public void whenSaveManyToManyRelationship_thenCorrect() {
		Author author1 = new Author(AUTHOR_NAME);
		template.postForEntity(AUTHOR_ENDPOINT, author1, Author.class);
		try {
			ResponseEntity<Author> authorGetResponse1 = template.getForEntity(AUTHOR_ENDPOINT + "/1", Author.class);
			assertEquals(true, authorGetResponse1.hasBody());
		} catch (java.lang.NullPointerException e) {
			e.printStackTrace();
		}
		Author author2 = new Author(AUTHOR_NAME + " clone");
		template.postForEntity(AUTHOR_ENDPOINT, author2, Author.class);
		try {
			ResponseEntity<Author> authorGetResponse2 = template.getForEntity(AUTHOR_ENDPOINT + "/2", Author.class);
			assertEquals(true, authorGetResponse2.hasBody());
		} catch (java.lang.NullPointerException e) {
			e.printStackTrace();
		}

		Book book1 = new Book("Animal Farm");
		var resBook1 = template.postForEntity(BOOK_ENDPOINT, book1, Book.class);
		assertEquals(true, resBook1.hasBody(), "resBook1 doesn't have body");

		Book book2 = new Book("1985");
		var resBook2 = template.postForEntity(BOOK_ENDPOINT, book2, Book.class);
		assertEquals(true, resBook2.hasBody(), "resBook2 doesn't have body");

		var resBooks = template.getForEntity(LIBRARY_RESOURCE_ENDPOINT, Book[].class);
		assertEquals(true, resBooks.getStatusCode().is2xxSuccessful(), "resBook failed");

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Content-type", "text/uri-list");
		HttpEntity<String> httpEntity = new HttpEntity<>(
				BOOK_ENDPOINT + "/" + resBooks.getBody()[0].getId() + "\n" + BOOK_ENDPOINT + "/"
						+ resBooks.getBody()[1].getId(),
				requestHeaders);
		template.exchange(AUTHOR_ENDPOINT + "/1/books",
				HttpMethod.PUT, httpEntity, String.class);

		try {
			String jsonResponse = template
					.getForObject(BOOK_ENDPOINT + "/" + resBooks.getBody()[0].getId() + "/authors",
							String.class);
			JSONObject jsonObj = new JSONObject(jsonResponse).getJSONObject("_embedded");
			JSONArray jsonArray = jsonObj.getJSONArray("authors");
			assertEquals(AUTHOR_NAME,
					jsonArray.getJSONObject(0).getString("name"), "author is incorrect");
		} catch (JSONException | java.lang.NullPointerException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void whenSaveManyToManyRelationshipGroupAuthor_thenCorrect() {
		GroupAuthor group1 = new GroupAuthor("Group 1");
		template.postForEntity(GROUP_ENDPOINT, group1, GroupAuthor.class);

		GroupAuthor group2 = new GroupAuthor("Group 2");
		template.postForEntity(GROUP_ENDPOINT, group2, GroupAuthor.class);

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Content-type", "text/uri-list");
		HttpEntity<String> httpEntity = new HttpEntity<>(
				AUTHOR_ENDPOINT + "/1\n" + AUTHOR_ENDPOINT + "/2", requestHeaders);
		var resUpdate1 = template.exchange(GROUP_ENDPOINT + "/1/authors",
				HttpMethod.PUT, httpEntity, String.class);
		assertEquals(true, resUpdate1.getStatusCode().is2xxSuccessful());

		HttpHeaders requestHeaders1 = new HttpHeaders();
		requestHeaders1.add("Content-type", "text/uri-list");
		HttpEntity<String> httpEntity1 = new HttpEntity<>(
				AUTHOR_ENDPOINT + "/1\n" + AUTHOR_ENDPOINT + "/2", requestHeaders1);
		var resUpdate2 = template.exchange(GROUP_ENDPOINT + "/2/authors",
				HttpMethod.PUT, httpEntity1, String.class);
		assertEquals(true, resUpdate2.getStatusCode().is2xxSuccessful());
	}

}
