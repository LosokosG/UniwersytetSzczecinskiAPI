LABEL authors="Mateusz Macutkiewicz"
# Stage 1: Build the application
FROM gradle:7.3.3-jdk16 as builder

# Copy the source code to the container
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

# Compile and package the application
RUN gradle build --no-daemon

# Stage 2: Create the runtime image
FROM openjdk:16-alpine

# Copy the built JAR from the builder stage
COPY --from=builder /home/gradle/src/build/libs/UniwersytetSzczecinskiAPI-1.0-plain.jar .

# Expose the port your app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","./UniwersytetSzczecinskiAPI-1.0-plain.jar.jar"]
