# Multi-stage build for Maven Spring Boot application
# Stage 1: Build the application
FROM maven:3.9.12-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy the pom.xml and download dependencies (this layer will be cached)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:17-jre-alpine

# Set working directory
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Set JVM options (optional, adjust as needed)
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]