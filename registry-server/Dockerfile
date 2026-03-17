# rara vez se construlle la imagen de despliege con los test
# Se debe instalar apache maven
# Se usa para construir la imagen, se crea la carpeta target y esta el .jar
# mvn clean install -DskipTests=true

FROM eclipse-temurin:25-jdk-alpine
VOLUME /tmp
COPY target/registry-server-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
# Se usa para crear la iamgen
# docker build -t ms-registry-server:latest .

# Luego de crear la imagen creamos el contenedor
# docker run -p 8761:8761 --name test-ms a8da05685ccd