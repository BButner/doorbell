FROM openjdk:11

WORKDIR /tmp

COPY ./doorbell-application/target/doorbell-application-1.0-SNAPSHOT.jar /tmp/doorbell.jar

EXPOSE 8080

CMD ["java","-jar","/tmp/doorbell.jar"]