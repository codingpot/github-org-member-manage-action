FROM openjdk:11

ENV APP_HOME=/codingpot
WORKDIR $APP_HOME

COPY build.gradle.kts settings.gradle.kts gradlew $APP_HOME/
COPY gradle $APP_HOME/gradle/
COPY app/build.gradle $APP_HOME/app/

RUN ./gradlew resolveDependencies

COPY . $APP_HOME
RUN ./gradlew build

ENTRYPOINT java -jar app/build/libs/app.jar
