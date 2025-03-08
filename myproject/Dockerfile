FROM eclipse-temurin:23-jdk

ARG JAR_FILE=build/libs/myproject-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} backend.jar

RUN apt-get update && apt-get install -y ca-certificates

ENTRYPOINT ["java", "-jar", "backend.jar"]

EXPOSE 8080