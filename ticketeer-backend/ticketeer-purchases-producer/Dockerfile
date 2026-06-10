FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/ticketeer-purchases-producer*.jar ticketeer-purchases-producer.jar
EXPOSE 8091
ENTRYPOINT ["java","-jar","/app/ticketeer-purchases-producer.jar"]