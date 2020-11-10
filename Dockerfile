FROM openjdk:8-alpine
ADD target/machineapi-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD java -jar machineapi-0.0.1-SNAPSHOT.jar