# Use the official OpenJDK 17 image as a base
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the target directory into the container
ADD target/gestion-station-ski-1.0.jar /app/gestion-station-ski.jar

# Expose the port on which the application will run
EXPOSE 8080

# Command to run the JAR file
CMD ["java", "-jar", "gestion-station-ski.jar"]