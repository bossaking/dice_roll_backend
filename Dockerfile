FROM openjdk:20-oracle

# Set the working directory to /app
WORKDIR /app

# Copy the packaged Spring Boot application JAR file to the container
COPY target/Dice_Roll-0.0.1-SNAPSHOT.jar .

# Expose port 8080
EXPOSE 8080

# Set the command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "Dice_Roll-0.0.1-SNAPSHOT.jar"]