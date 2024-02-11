FROM openjdk:17.0.1-jdk-slim
ARG JAR_FILE=*.war
COPY ${JAR_FILE} /DgaBoard.war
ENTRYPOINT ["java","-jar", "/DgaBoard.war"]