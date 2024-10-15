# Build stage
FROM maven:3.9.4-eclipse-temurin-17 AS BUILD
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17-oracle
ENV ARTIFACT_NAME=spring-task2-0.0.1-SNAPSHOT.jar
WORKDIR /app
COPY --from=BUILD /app/target/$ARTIFACT_NAME .

ENTRYPOINT exec java -jar ${ARTIFACT_NAME}
