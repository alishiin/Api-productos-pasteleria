# Imagen base con Java 17
FROM eclipse-temurin:17-jdk

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos todo el proyecto
COPY . .

# Damos permiso de ejecución al wrapper de Maven
RUN chmod +x mvnw

# Compilamos el proyecto sin ejecutar tests
RUN ./mvnw clean package -DskipTests

# Exponemos el puerto del backend
EXPOSE 8080

# Ejecutamos la aplicación
CMD ["java", "-jar", "target/Api-productos-pasteleria-0.0.1-SNAPSHOT.jar"]
