# Imagen base con Java 17
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copia el JAR generado por Maven
COPY target/clinic-appointment-system-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8091

ENTRYPOINT ["java", "-jar", "app.jar"]
