FROM gradle:8.7-jdk17 AS builder

WORKDIR /app
COPY . .

RUN gradle build -x test --no-daemon

FROM eclipse-temurin:17-jre

WORKDIR /app
COPY --from=builder /app/build/libs/*.jar agendador-tarefas.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "agendador-tarefas.jar"]