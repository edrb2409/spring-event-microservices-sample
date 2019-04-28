#!/bin/bash

echo "Dockerizing employee-service"
cd employee-service && ./gradlew build docker

echo "Dockerizing event-service"
cd ../event-service && ./gradlew build docker

cd ../compose-services && docker-compose up -d