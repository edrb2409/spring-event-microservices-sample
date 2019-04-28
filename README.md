# Microservices sample

This repository contains two services:

- employee service
- event service

The employee-service manages the CRUD operations for employees and sends an event when an operations occurs, this event is sent thru rabbitMQ. The event-service stores the events received and exposes this data thru API.

## Getting Started

These instructions will run the components in a docker-compose configuration with complementary services on your local machine for development and testing purposes. For detailed information see the README.md file on each component.

### Prerequisites

- Java 11
- Docker
- Docker Compose

### Installing

Run the following script for creating the components containers.

```
./build.sh
```

### Deployment

Go to `compose-services` and run the following command for starting up the services:

```
docker-compose up -d
```


## Author

- Daniel Ron - [edrb-profile](https://edrb.github.io)