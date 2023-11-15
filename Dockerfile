FROM openjdk:11-jre-slim
WORKDIR /app

COPY build/libs/meta-mingle-0.0.1-SNAPSHOT.jar ameta-mingle-0.0.1-SNAPSHOT.jar
COPY src/main/resources/meta-mingle-firebase-key.json meta-mingle-firebase-key.json

CMD ["java", "-jar", "meta-mingle-0.0.1-SNAPSHOT.jar"]