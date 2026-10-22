FROM ubuntu:latest
LABEL authors="JEEVA"

ENTRYPOINT ["top", "-b"]

FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

.git
.idea
target
*.iml
.env
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]