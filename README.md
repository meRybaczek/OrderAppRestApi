# OrderAppRestApi
The Restful API is designed to handle clients, their orders, and a list of PDF files. This API is a microservice component of an application that manages orders in a large-format digital printing company. The API works in conjunction with another microservice called "podliczacz," which is responsible for calculating the surface areas of clients' PDFs and estimating the price for client orders. The OrderRestApi and podliczacz microservices communicate with each other to facilitate the order management process.
...
The OrderAppRestApi application can be containerized and deployed using Docker. To create the Docker image, you can use a Dockerfile that contains the necessary instructions for building the image. Once the image is created, you can deploy the application using docker-compose.

The docker-compose configuration includes two additional containers: one for the MySQL database using the mysql image and another for Adminer, which provides a user interface to preview the database. The MySQL container is linked to the OrderAppRestApi container, allowing the application to connect to the database.

The OrderAppRestApi exposes its API on port 8090, allowing clients to interact with the application's endpoints.
