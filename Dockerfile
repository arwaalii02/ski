# Stage 1: Build the Maven project
FROM maven:3.8.1-openjdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the application (runtime image)
FROM openjdk:11-jre

# Install Maven in the runtime container for testing purposes
RUN apt-get update && apt-get install -y maven

WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Set the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
