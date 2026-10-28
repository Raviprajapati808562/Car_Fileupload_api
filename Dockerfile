
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY main.jar /app/main.jar
COPY car.jar /app/car.jar
COPY start.sh /app/start.sh

RUN chmod +x /app/start.sh

EXPOSE 9091 8081

ENTRYPOINT ["/bin/sh", "/app/start.sh"]
