FROM openjdk:11 as build

ENV APP_HOME=/codingpot
WORKDIR $APP_HOME

COPY build.gradle.kts settings.gradle.kts gradlew $APP_HOME/
COPY gradle $APP_HOME/gradle/
COPY app/build.gradle $APP_HOME/app/

RUN ./gradlew resolveDependencies

COPY . $APP_HOME
RUN ./gradlew build

FROM gcr.io/distroless/java:11
COPY --from=build /codingpot/app/build/libs/app.jar /codingpot/
CMD ["/codingpot/app.jar"]
