# Etapa 1: Construir el proyecto usando Maven
FROM maven:3.9.6-eclipse-temurin-22 AS build

WORKDIR /app

# Copiar pom.xml y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente
COPY src ./src
COPY resources/wallet ./wallet

# Compilar el JAR sin ejecutar tests
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final más liviana
FROM eclipse-temurin:22-jre

WORKDIR /app

# Copiar el JAR generado desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar
COPY --from=build /app/wallet ./wallet

# Puerto que expone tu API (normalmente 8080)
EXPOSE 8080

# Comando de ejecución
ENTRYPOINT ["java", "-jar", "app.jar"]
