FROM openjdk:11-jdk

ARG JAR_FILE=build/libs/*.jar
COPY /home/circleci/project/build/libs/aws-integration-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]