# Employee Service

Store and manage employees and departments

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

- Java 11
- Docker
- Postgresql
- RabbitMQ

### Installing

Compilation

```
./gradlew build
```

Create docker image (edrb/employee-service)

```
./gradlew build docker
```

## Swagger page

`http://localhost:8081/swagger-ui.html`

## Running the test cases

Run the test cases execute

```
./gradlew test
```

## Deployment

For deployment you have to have Docker installed.

### From code source

Create docker image (edrb/employee-service)

```
/.gradlew build docker
```

## Build With

- SpringBoot
- Spring Framework (Core, Data, Web)
- JUnit 5 Jupiter
- Mockito
- Gradle
- Postgresql
- RabbitMQ

## Author

- Daniel Ron - [edrb-profile](https://edrb.github.io)
