FROM gradle:8.7-jdk17 as builder

ADD / ./
ADD build.gradle ./
ADD settings.gradle ./
ADD gradlew ./
ADD gradle ./gradle

RUN chmod +x gradlew && sed -i -e 's/\r$//' gradlew && ./gradlew clean build

FROM openjdk:17-alpine
ARG SERVICE_NAME

COPY --from=builder /home/gradle/$SERVICE_NAME/build/libs/$SERVICE_NAME-0.0.1-SNAPSHOT.jar ./app.jar
ENTRYPOINT ["java","-jar", "app.jar"]