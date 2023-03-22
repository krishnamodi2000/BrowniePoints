#  Stage 1: Build the application with Maven
FROM maven:3.8.3 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ /app/src/
RUN mvn package -DskipTests

# Stage 2: Run the application with Java
FROM openjdk:17
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/BPA.jar
ENTRYPOINT ["java", "-jar", "BPA.jar"]
