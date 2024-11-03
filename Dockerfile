FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD target/ski-1.0.jar ski-1.0.jar
ENTRYPOINT ["java","-jar","/ski-1.0.jar"]
