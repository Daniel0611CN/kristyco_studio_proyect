FROM maven:3-amazoncorretto-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto:21-alpine
COPY --from=build /target/proyectoServidor-0.0.1-SNAPSHOT.jar kristyco_back.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","kristyco_back.jar"]
