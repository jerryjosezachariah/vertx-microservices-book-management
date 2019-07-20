package org.jerry.microservices.vertx.workerVerticles;

import org.jerry.microservices.vertx.model.Book;
import org.jerry.microservices.vertx.service.BookService;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

public class CreateBookVerticle extends AbstractVerticle {

	@Override
	public void start(Future<Void> future) throws Exception {

		System.out.println("CreateBookVerticle started");
		MessageConsumer<JsonObject> consumer = vertx.eventBus().consumer("createBookVerticle");
		consumer.handler(event -> {
			Book book = Json.decodeValue(event.body().getString("book"), Book.class);
			BookService.createBook(book);
			event.reply(book.getIsbn());
		});
		
		future.complete();
	}
}
