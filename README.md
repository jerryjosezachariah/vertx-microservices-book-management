# vertx-microservices-book-management

This is a learning project for microservices based project that is powered by Vertx at the backend.

This project exposes RESTful web service end points to create and retrieve Books from a repository.

It showcases EventBus based inter verticle communication, both point-to-point as well as broadcast models.
An asynchronous design is maintained for both application and Junits.

A main verticle deploys the other verticles. A front facing RestVerticle exposes the REST end points.
Worker verticles deployed in the background process the REST calls. Each REST end point is processed by a separate microservice.

Once a book has been created, a notification is sent to the user (stubbed) via email and sms.

### Available Services and their JSON payload
| HTTP METHOD | PATH | USAGE | PAYLOAD |
| -----------| ------ | ------ |------|
| GET | /books/ | get all books | |
| GET | /books/{isbn} | get book by isbn | |
| POST | /books/ | create a new book | {"isbn": 9,"title": "War of the Worlds","price":999} |


  
  
  

