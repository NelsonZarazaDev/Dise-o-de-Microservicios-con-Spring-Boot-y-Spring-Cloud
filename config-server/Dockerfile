FROM eclipse-temurin:25-jdk-alpine
VOLUME /tmp
COPY target/config-server-0.0.1-SNAPSHOT.jar app.jar
ENV EUREKA_URL null
ENTRYPOINT ["java","-jar","/app.jar"]