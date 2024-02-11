FROM openjdk:17.0.1-jdk-slim
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} /DgaBoard.jar
ENTRYPOINT ["java","-jar", "/DgaBoard.jar"]
