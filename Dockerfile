# Use an official OpenJDK runtime as a parent image
FROM openjdk17:alpine-jre

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container at /app
COPY . .

# Expose the port that your application will run on
EXPOSE 8080

# Specify the command to run on container startup
CMD ["java", "-jar", "app.jar"]


# Cấu hình biến môi trường
#ENV SPRING_DATASOURCE_URL=jdbc:postgresql://dpg-ckg4se8l3its73dig5pg-a.singapore-postgres.render.com:5432/aomanager
#ENV SPRING_DATASOURCE_USERNAME=aomanager_user
#ENV SPRING_DATASOURCE_PASSWORD=TEeNGWhSc3tgrAqaCfzyEiLtq6hWqNYp