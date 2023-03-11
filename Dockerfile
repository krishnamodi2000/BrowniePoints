FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} BPA.jar
ENTRYPOINT ["java", "-jar", "/BPA.jar"]
