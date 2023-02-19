FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/kameleon-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} kameleon-backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","kameleon-backend.jar"]