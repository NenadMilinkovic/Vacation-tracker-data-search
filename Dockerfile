FROM gradle:7.6.1-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon
FROM openjdk:18
WORKDIR /app
COPY --from=build build/libs/dataSearch-0.0.1-SNAPSHOT.jar dataSearch.jar
EXPOSE 8081
CMD ["java", "-jar", "dataSearch.jar"]
