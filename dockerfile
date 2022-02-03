FROM openjdk:11
# VOLUME /tmp
ARG JAR_FILE=./build/libs/*.jar
RUN mkdir app
WORKDIR ./app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","./app.jar"]

# FROM openjdk:11 AS builder
# COPY gradlew .
# COPY gradle gradle
# COPY build.gradle .
# COPY settings.gradle .
# COPY src src
# RUN chmod +x ./gradlew
# RUN ./gradlew bootJar
#
# FROM openjdk:11
# COPY --from=builder build/libs/*.jar app.jar
# EXPOSE 8080
# ENTRYPOINT ["java","-jar","/app.jar"]
