package org.jerry.microservices.vertx.controller;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class RestVerticle extends AbstractVerticle {

	@Override
	public void start(Future<Void> future) throws Exception {
		System.out.println("RestVerticle started");
		Router router = Router.router(vertx);

		router.get("/books").handler(this::getAllBooks);
		router.get("/books/:isbn").handler(this::getBookById);
		router.post("/books/").handler(BodyHandler.create()).handler(this::createBook);

		vertx.createHttpServer().requestHandler(router).listen(8080, result -> {
			if (result.succeeded()) {
				future.complete();
			} else {
				future.fail(result.cause());
			}
		});
	}

	private void getAllBooks(RoutingContext context) {
		vertx.eventBus().send("getAllBooksVerticle", new JsonObject(), response -> {
			context.response().putHeader("Content-Type", "application/json").end(response.result().body().toString());
		});
	}

	private void getBookById(RoutingContext context) {
		vertx.eventBus().send("getBookByIdVerticle",
				new JsonObject().put("isbn", Integer.parseInt(context.request().getParam("isbn"))), response -> {
					context.response().putHeader("Content-Type", "application/json")
							.end(response.result().body().toString());
				});
	}

	private void createBook(RoutingContext context) {
		vertx.eventBus().send("createBookVerticle", new JsonObject().put("book", context.getBodyAsString()),
				response -> {
					
					vertx.eventBus().publish("notification", response.result().body().toString());
					
					context.response().putHeader("Content-Type", "application/json").setStatusCode(201)
							.putHeader("Location",
									context.request().absoluteURI() + "/" + response.result().body().toString())
							.end(context.getBodyAsString());
				});
	}
}
