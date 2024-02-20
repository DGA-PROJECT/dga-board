FROM openjdk:17.0.1-jdk-slim
ARG JAR_FILE=build/libs/dga-0.0.1-SNAPSHOT.war
COPY ${JAR_FILE} ./DgaBoard.war
ENTRYPOINT ["java","-jar", "./DgaBoard.war"]
