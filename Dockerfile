#FROM openjdk:17-jdk
#WORKDIR /app
#
#COPY target/*.jar app.jar
#
#ENTRYPOINT ["java", "-jar", "/app.jar"]
#EXPOSE 8080

#
# Build stage
#
FROM maven:3.6.0-jdk-11 AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
FROM openjdk:17-jdk-slim
COPY --from=build /target/shoes-ecommerce-0.0.1-SNAPSHOT.jar app.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]