package org.jerry.microservices.vertx.controller;

import java.io.IOException;

import org.jerry.microservices.vertx.MainVerticle;
import org.jerry.microservices.vertx.model.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;


@RunWith(VertxUnitRunner.class)
public class RestVerticleTest {
	
	private Vertx vertx;
	
	@Before
	public void setUP(TestContext context) throws IOException {
		vertx = Vertx.vertx();
		vertx.deployVerticle(MainVerticle.class.getName(), context.asyncAssertSuccess());
	}
	
	@After
	public void tearDown(TestContext context){
		vertx.close(context.asyncAssertSuccess());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testBookCreation(TestContext context){
		System.out.println("Testing book creation.");
		
		Book expectedBook = new Book(9,"War of the Worlds",999);
		String json = Json.encodePrettily(expectedBook);
		
		final Async async = context.async();
		
		vertx.createHttpClient().post(8080, "localhost", "/books").putHeader("Content-Type","application/json").putHeader("Content-Length", Integer.toString(json.length())).handler(response -> {
			context.assertEquals(response.statusCode(), 201);
			context.assertTrue(response.headers().get("Content-Type").contains("application/json"));
			context.assertTrue(response.headers().get("Location") != null);
			response.bodyHandler(body -> {
				Book actualBook = Json.decodeValue(body.toString(), Book.class);
				context.assertEquals(expectedBook.getIsbn(), actualBook.getIsbn());
				context.assertEquals(expectedBook.getPrice(), actualBook.getPrice());
				context.assertEquals(expectedBook.getTitle(), actualBook.getTitle());
				async.complete();
			});
		}).write(json).end();
	}
}
