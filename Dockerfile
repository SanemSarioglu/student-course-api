# Stage 1: build the JAR
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: run the JAR
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Mount point for your persistent data
VOLUME /data

# Expose your API port
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
