FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-alpine
COPY --from=build /target/proyectoServidor.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
