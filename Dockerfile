# Fase de compilación: utiliza una imagen de Maven para compilar la aplicación
FROM maven:3.8.4-openjdk-11 AS builder
#Autor
LABEL authors="geraldinelopez"
# Configura el directorio de trabajo
WORKDIR /app

# Copia el archivo pom.xml para descargar las dependencias
COPY pom.xml .

# Descarga las dependencias del proyecto
RUN mvn dependency:go-offline

# Copia los archivos del proyecto al contenedor
COPY src ./src

# Limpiar antes de compilar y compilar
RUN mvn clean package install

# Fase de ejecución: utiliza una imagen de OpenJDK para ejecutar la aplicación
FROM openjdk:11-jre-slim

# Configura el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR construido desde la imagen de compilación
COPY --from=builder /app/target/customerKata-1.0-SNAPSHOT.jar .

# Expone el puerto 8080
EXPOSE 8080

# Comando por defecto para ejecutar la aplicación
CMD ["java", "-jar", "customerKata-1.0-SNAPSHOT.jar"]

