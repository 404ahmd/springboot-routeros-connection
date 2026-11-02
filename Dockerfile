# Stage 1: Build aplikasi
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /var/www/springboot-routeros
# Copy file konfigurasi dan source code ke dalam builder
COPY pom.xml .
COPY src ./src
# Build jar file
RUN mvn clean package -DskipTests

# Stage 2: Setup environment untuk menjalankan aplikasi
FROM eclipse-temurin:17-jre-alpine
WORKDIR /var/www/springboot-routeros
# Ambil file .jar hasil build dari stage 1
COPY --from=builder /var/www/springboot-routeros/target/*.jar app.jar

# Expose port yang digunakan (sebagai dokumentasi internal Docker)
EXPOSE 8081

# Jalankan aplikasi
ENTRYPOINT ["java", "-jar", "app.jar"]