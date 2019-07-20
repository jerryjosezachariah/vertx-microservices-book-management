package org.jerry.microservices.vertx.workerVerticles;

import org.jerry.microservices.vertx.model.Book;
import org.jerry.microservices.vertx.service.BookService;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

public class EmailVerticle extends AbstractVerticle {

	@Override
	public void start(Future<Void> future) throws Exception {
		System.out.println("Started email verticle");
		
		MessageConsumer<JsonObject> consumer = vertx.eventBus().consumer("notification");
		consumer.handler(event -> {
			System.out.println("Email has been sent for notifying on new Book added to repo.");
			
		});
		
		future.complete();
	}
}
