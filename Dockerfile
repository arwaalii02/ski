# Stage 1: Build the Maven project
FROM maven:3.8.1-openjdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the application (runtime image)
FROM openjdk:11-jre
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Install Maven in the runtime image
RUN apt-get update && apt-get install -y \
    wget \
    && wget https://archive.apache.org/dist/maven/maven-3/3.8.1/binaries/apache-maven-3.8.1-bin.tar.gz \
    && tar -xvzf apache-maven-3.8.1-bin.tar.gz -C /opt/ \
    && ln -s /opt/apache-maven-3.8.1/bin/mvn /usr/local/bin/mvn

# Set the default command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
