FROM eclipse-temurin:17-centos7

ENV MICRONAUT_ENVIRONMENTS=homolog

RUN mkdir app
WORKDIR app

COPY main/layers/libs /app/libs
COPY main/layers/classes /app/classes
COPY main/layers/resources /app/resources
COPY main/layers/application.jar /app/application.jar

CMD ["java", "-Xmx256m", "-jar", "application.jar"]
EXPOSE 8080
