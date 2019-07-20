package org.jerry.microservices.vertx.service;


import java.util.List;

import org.jerry.microservices.vertx.dao.BookRepo;
import org.jerry.microservices.vertx.model.Book;

public class BookService {
	public static Book createBook(Book book){
		return BookRepo.createBook(book);
	}
	
	public static List<Book> getAllBooks() {
		return BookRepo.getAllBooks();
	}

	public static Book getBookById(Integer isbn) {
		return BookRepo.getBookById(isbn);
	}

}
