# Product Catalog Management

This project provides a comprehensive system for managing product catalogs, 
including features for products, categories, and catalogs, as well as integrations 
with **AWS services** like **S3**, **SQS** and **SNS** for storage and messaging.
It is a solution for this [challenge](https://github.com/githubanotaai/new-test-backend-nodejs).

## Features

- **Product Management**: CRUD operations for products, including association with categories.
- **Category Management**: CRUD operations for categories.
- **Catalog Management**: Manage and update catalogs with associated products and categories.
- **AWS Integration**:
  - **S3**: Storage of catalog data.
  - **SNS**: Publishing catalog-related events.
  - **SQS**: Consuming messages related to catalog updates.
- **MongoDB Integration**: For database operations using a flexible schema.

## Project Structure

### `application`
Contains the core business logic and use cases for the application, including:

- **Products**: Logic for registering, updating, deleting, and retrieving products.
- **Categories**: Similar use cases for category management.
- **Catalogs**: Logic for building and updating catalogs.
- **Events**: Handling and serialization of catalog-related events.

### `domain`
Defines the core domain models and exceptions:

- **Models**: `Product`, `Category`, `Catalog`, and related structures.
- **Exceptions**: Custom exceptions for domain-specific errors.

### `infrastructure`
Handles all external integrations and configurations:

- **Adapters**:
  - **Persistence**: MongoDB collections, repositories, and mappers for database operations.
  - **Messaging**: AWS SNS for publishing and SQS for consuming messages.
  - **Storage**: AWS S3 for catalog storage.
- **Configuration**: Async processing, AWS credentials, and scheduled tasks.
- **Controllers**: REST controllers for exposing APIs.
- **DTOs**: Request and response data transfer objects.
- **Exception Handling**: Centralized error handling for REST APIs.

### `ports`
Defines the interfaces for input (driven) and output (driving) adapters:

- **Input Ports**: Interfaces for application services.
- **Output Ports**: Interfaces for external integrations (e.g., database, storage, messaging).

## Key Classes

- **MongoConfig**: Configuration for MongoDB.
- **S3CatalogStorageAdapter**: Handles catalog storage in AWS S3.
- **SnsCatalogPublisherAdapter**: Publishes catalog events to AWS SNS.
- **SqsCatalogConsumerAdapter**: Consumes messages with catalog events from AWS SQS.
- **ProductRestController**: REST API for managing products.
- **CategoryRestController**: REST API for managing categories.
- **CatalogRestController**: REST API for managing catalogs.

## How to Run

1. **Prerequisites**:
   - Java 21 or higher
   - Maven 3.8.1 or higher
   - MongoDB instance running
   - AWS credentials configured for S3, SNS, and SQS access

2. **Clone this repository**:
```bash
   git https://github.com/agmgomes/product-catalog-management.git
   cd product-catalog-management
```

3. **Build the project**:
   ```bash
   mvn clean install
   ```

4. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

5. **Access the APIs**:
   - Product APIs: `/api/product`
   - Category APIs: `/api/category`
   - Catalog APIs: `/api/catalog`

## Configuration

### Application Properties

Configure the following properties in `application.properties`:

```properties
spring.data.mongodb.uri=mongodb://<host>:<port>/<database>
aws.access.key.id=<aws-access-key>
aws.secret.key=<aws-secret-key>
aws.region=<aws-region>
aws.s3.bucket.name=<bucket-name>
aws.sns.topic.arn=<sns-topic-arn>
aws.sqs.queue.url=<sqs-queue-url>
```

### AWS Credentials

Ensure AWS credentials are configured in your environment for accessing S3, SNS, and SQS.

### MongoDB Instance

Ensure that you have a MongoDB instance configured in your environment or use
a Docker Container:

```bash
   docker compose up -d
```
This container starts a MongoDB service with the following properties:

```properties
   username=root
   password=password
   database=product-catalog
```

## Contribution

Feel free to fork the repository, create issues, and submit pull requests.
Contributions are welcome!