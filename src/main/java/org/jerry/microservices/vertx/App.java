package org.jerry.microservices.vertx;

import io.vertx.core.Vertx;

public class App {

	public static void main(String[] args) {

		System.out.println("Main App started");
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(MainVerticle.class.getName());
	}

}
