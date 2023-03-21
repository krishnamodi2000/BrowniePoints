FROM openjdk:17
EXPOSE 8080/tcp
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]
