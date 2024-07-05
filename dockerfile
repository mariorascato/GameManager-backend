FROM maven:latest AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=build /target/game_manager-1.jar spring.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","spring.jar"]