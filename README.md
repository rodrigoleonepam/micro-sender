# Microservices with RabbitMQ
Author: Rodrigo León N.

This microservice put and retrieve messages in a RabbitMQ queue.
A listener reads messages from the queue and put them in a queue in memory.
java.util.Queue.

### Endpoints
* Send notification
http://localhost:8080/notification
* Retrieve notification
http://localhost:8080/retrieve

The microservices can be monitored using Prometheus.
http://localhost:8080/actuator/prometheus