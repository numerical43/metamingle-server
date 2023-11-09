FROM openjdk:11-jre-slim
COPY build/libs/meta-mingle-0.0.1-SNAPSHOT.jar /meta-mingle-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "/meta-mingle-0.0.1-SNAPSHOT.jar"]
