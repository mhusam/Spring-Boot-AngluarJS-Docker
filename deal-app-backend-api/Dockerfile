FROM openjdk:8-jdk-alpine

EXPOSE 8088

ADD target/deal-app-backend-api-1.0.jar deal-app-backend-api-1.0.jar

ENTRYPOINT ["java", "-jar", "deal-app-backend-api-1.0.jar"]