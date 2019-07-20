package org.jerry.microservices.vertx;

import org.jerry.microservices.vertx.controller.RestVerticle;
import org.jerry.microservices.vertx.workerVerticles.CreateBookVerticle;
import org.jerry.microservices.vertx.workerVerticles.EmailVerticle;
import org.jerry.microservices.vertx.workerVerticles.GetAllBooksVerticle;
import org.jerry.microservices.vertx.workerVerticles.GetBookByIdVerticle;
import org.jerry.microservices.vertx.workerVerticles.SMSVerticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MainVerticle extends AbstractVerticle {

	@Override
	public void start(Future<Void> future) throws Exception {

		System.out.println("MainVerticle started");
		vertx.deployVerticle(RestVerticle.class.getName());
		vertx.deployVerticle(GetAllBooksVerticle.class.getName());
		vertx.deployVerticle(GetBookByIdVerticle.class.getName());
		vertx.deployVerticle(CreateBookVerticle.class.getName());
		vertx.deployVerticle(EmailVerticle.class.getName());
		vertx.deployVerticle(SMSVerticle.class.getName());

		future.complete();
	}
}
