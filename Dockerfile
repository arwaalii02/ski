# Stage 1: Build the Maven project
FROM maven:3.8.1-openjdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the application (runtime image)
FROM openjdk:11-jre
WORKDIR /app

# Copy the Maven installation from the build stage
COPY --from=build /usr/share/maven /usr/share/maven
COPY --from=build /app/target/*.jar app.jar

# Set the Maven path
ENV MAVEN_HOME /usr/share/maven
ENV PATH $MAVEN_HOME/bin:$PATH

ENTRYPOINT ["java", "-jar", "app.jar"]
