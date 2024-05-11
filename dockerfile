FROM openjdk:17-oracle

WORKDIR /app

COPY jwt-validation.jar .

EXPOSE 8080

CMD ["java", "-jar", "jwt-validation.jar"]