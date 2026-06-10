FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/ticketeer-purchases-worker*.jar ticketeer-purchases-worker.jar
ENTRYPOINT ["java","-jar","/app/ticketeer-purchases-worker.jar"]