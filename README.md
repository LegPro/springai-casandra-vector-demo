
# Spring AI Cassandra Vector Demo

This project demonstrates how to use Apache Cassandra as a vector store in a Spring Boot application, with functionality for storing documents, generating vector embeddings, and performing similarity searches. This project also integrates Spring AI for handling documents and embedding transformations.

## Prerequisites

- **Java 21**
- **Maven**
- **Docker**
- **Apache Cassandra** (running in a Docker container)
- **Spring Boot**
- **Spring AI**

## Getting Started

### 1. Run Cassandra in Docker

You can quickly spin up a Cassandra instance using Docker. Run the following command:

```bash
docker run -p 7000:7000 -p 7001:7001 -p 7199:7199 -p 9042:9042 -p 9160:9160 --name cassandra -d cassandra:latest
```

This will start a Cassandra container exposing the necessary ports for communication.

### 2. Create Cassandra Keyspace and Table

it will be automatically created by the application when you run it.

```sql
### 3. Configure Spring Boot Application

add your openai key to the application.properties file

```properties
### 4. Running the Application

Once you've configured Cassandra and Spring Boot, you can run the application using Maven:

```bash
mvn spring-boot:run
```

### 5. REST Endpoints

The project exposes two key REST API endpoints:

1. **Load Documents**
    - URL: `/load`
    - Description: This endpoint loads and stores documents into the vector store.
    - Example:
   ```bash
   curl http://localhost:8080/load
   ```

2. **Search Documents**
    - URL: `/search`
    - Description: This endpoint searches for documents similar to the provided query using cosine similarity.
    - Example:
   ```bash
   curl "http://localhost:8080/search?query=The world is big"
   ```

### 6. Code Overview

- **Service Layer** (`AICassandraVectorService`):
    - Responsible for loading documents and handling similarity searches.
    - Uses the `VectorStore` to add documents and search them based on vector similarity.

- **Controller Layer** (`AIController`):
    - Provides REST APIs to load documents and search documents via HTTP requests.

### 7. Example Documents

Here are the example documents being added in the application:

```java
List<Document> documents = List.of(
    new Document("Spring AI Rocks! Spring AI Rocks!", Map.of("country", "UK", "year", 2019)),
    new Document("The world is big and salvation around the corner!", Map.of("country", "BG", "year", 2018)),
    new Document("You walk forward facing to the past and you turn back towards the future.", Map.of("country", "NL", "year", 2023)),
    new Document("Technology shapes the future but leaves our past behind.", Map.of("category", "Technology")),
    new Document("The evolution of artificial intelligence is transforming industries.", Map.of("category", "Technology", "year", 2021)),
    new Document("Books are uniquely portable magic.", Map.of("author", "Stephen", "genre", "Literature"))
);
```

The application splits these documents using a `TokenTextSplitter` and stores them in the Cassandra vector store for later retrieval.

### 8. Searching for Documents

When you send a query to the `/search` endpoint, the application searches the vector store for the two most similar documents based on the cosine similarity of their embeddings.
