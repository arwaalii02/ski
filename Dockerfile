# Stage 1: Build the Maven project
FROM maven:3.8.1-openjdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the application and make Maven available for tests
FROM openjdk:11-jre
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Install Maven in the runtime image for testing (Optional but needed for the exec command)
RUN apt-get update && apt-get install -y maven

# Set the default command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
