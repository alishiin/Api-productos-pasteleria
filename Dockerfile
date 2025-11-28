# Etapa 1: Construir el proyecto usando Maven
FROM maven:3.9.6-eclipse-temurin-22 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
COPY src/main/resources/wallet ./resources/wallet

RUN mvn clean package -DskipTests

# Etapa 2: Imagen final
FROM eclipse-temurin:22-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar
COPY --from=build /app/resources/wallet ./resources/wallet

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
