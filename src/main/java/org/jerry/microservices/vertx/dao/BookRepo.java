package org.jerry.microservices.vertx.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jerry.microservices.vertx.model.Book;

public class BookRepo {
	private static Map<Integer, Book> map = new HashMap<Integer, Book>();

	static {
		map.put(1, new Book(1, "Harry Potter", 100));
		map.put(2, new Book(2, "Tom Sawyer", 200));
		map.put(3, new Book(3, "The Jungle Book", 300));
	}

	public static List<Book> getAllBooks() {
		return map.values().stream().collect(Collectors.toList());
	}

	public static Book getBookById(Integer isbn) {
		return map.get(isbn);
	}

	public static Book createBook(Book book) {
		map.put(book.getIsbn(), book);
		return book;
	}
}
