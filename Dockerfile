# ---- Build stage ----
FROM gradle:8.11.1-jdk23-jammy AS builder
WORKDIR /home/gradle/src

# Leverage Docker layer caching
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
RUN chmod +x gradlew && ./gradlew --no-daemon dependencies

# Build the application
COPY src ./src
RUN ./gradlew --no-daemon clean bootJar

# ---- Runtime stage ----
FROM eclipse-temurin:23-jre
WORKDIR /app
COPY --from=builder /home/gradle/src/build/libs/*.jar /app/app.jar

EXPOSE 8080
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
