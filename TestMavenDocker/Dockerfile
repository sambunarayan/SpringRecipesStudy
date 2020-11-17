FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} test-maven-docker.jar
ENTRYPOINT ["java","-jar","/test-maven-docker.jar"]