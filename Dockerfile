FROM openjdk:18
WORKDIR /app
COPY build/libs/dataSearch-0.0.1-SNAPSHOT.jar dataSearch.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "dataSearch.jar"]

