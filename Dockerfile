
#
# Build stage
#
#FROM eclipse-temurin:17-jdk-jammy AS build
#COPY . .
#RUN ./mvnw clean package -Pprod -DskipTests

#
# Package stage
#
#FROM openjdk:17-jdk-slim
#COPY --from=build /target/shoes-ecommerce-0.0.1-SNAPSHOT.jar app.jar
# ENV PORT=8080
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","app.jar"]

# Use Maven image to build the application
FROM maven:3.6.3-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -Pprod -DskipTests

# Build the actual image
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/shoes-ecommerce-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

# Set environment variables
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://dpg-clus896d3nmc7387dr60-a.oregon-postgres.render.com:5432/wmshop_postgresql_database
ENV SPRING_DATASOURCE_USERNAME=wmshop_postgresql_database_user
ENV SPRING_DATASOURCE_PASSWORD=9882kkwr8ZR7MDgSjGlmLFDpUSQVbr7b
