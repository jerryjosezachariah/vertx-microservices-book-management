package org.jerry.microservices.vertx.workerVerticles;

import org.jerry.microservices.vertx.service.BookService;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

public class GetAllBooksVerticle extends AbstractVerticle {

	@Override
	public void start(Future<Void> future) throws Exception {

		System.out.println("GetAllBooksVerticle started");
		MessageConsumer<JsonObject> consumer = vertx.eventBus().consumer("getAllBooksVerticle");
		consumer.handler(event -> {
			event.reply(Json.encodePrettily(BookService.getAllBooks()));
		});
		
		future.complete();
	}
}
