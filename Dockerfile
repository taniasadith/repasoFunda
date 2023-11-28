FROM openjdk:17-alpine
COPY ./target/recomendacion-0.0.1-SNAPSHOT.jar /
RUN sh -c 'touch recomendacion-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","/recomendacion-0.0.1-SNAPSHOT.jar"]
RUN chmod +x /recomendacion-0.0.1-SNAPSHOT.jar