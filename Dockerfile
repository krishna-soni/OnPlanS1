FROM adoptopenjdk/openjdk11:latest
EXPOSE 8080 8081
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} onplans1.jar
ENTRYPOINT ["java","-jar","/onplans1.jar"]