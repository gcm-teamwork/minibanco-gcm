FROM eclipse-temurin:19-jdk-alpine

WORKDIR /app

COPY target/minibanco-gcm-1.0.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
