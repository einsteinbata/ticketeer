FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/ticketeer-api*.jar ticketeer-api.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/app/ticketeer-api.jar"]