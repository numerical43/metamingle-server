FROM openjdk:11-jre-slim
WORKDIR /home

COPY build/libs/meta-mingle-0.0.1-SNAPSHOT.jar /app/meta-mingle-0.0.1-SNAPSHOT.jar
COPY src/main/resources/meta-mingle-firebase-key.json /app/meta-mingle-firebase-key.json

CMD ["java", "-jar", "/app/meta-mingle-0.0.1-SNAPSHOT.jar"]