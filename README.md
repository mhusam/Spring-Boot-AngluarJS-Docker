# Spring-Boot-AngluarJS-Docker

### Overview
This is a Spring boot & AngularJS Application deployed in Docker compose.

### Prerequisites
1. AngularJS
    - Node
    - NPM
    - Bower

2. Spring Boot
    - Java 11
    - Sprint Boot 2.5.6.RELEASE
    - Maven latest
    - MySQL
3. Docker

## Getting Started
### link:Deployment Process
1. AngularJS
    - npm install
    - bower install
    - grunt serve
1. Spring Boot
    - mvn clean
    - mvn package
    - java -jar target/deal-app-backend-api-1.0.0.jar
## Docker Configuration
   - mvn clean install (Build the application)
   - mvn install dockerfile:build (Build the Docker Images)
   - docker images (For checking the Docker created Images )
   - docker run -p 8181:8181 -t springio/deal-app-backend-api (Run the Docker Image with port)
   - docker ps ( For seeing the running container)
   - docker stop **[Container Id]**
   - docker rm '[Container Id]'
   - docker Stop Container
