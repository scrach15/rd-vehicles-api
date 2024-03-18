FROM openjdk:21-slim-bookworm
EXPOSE 8080
WORKDIR /app
ARG JAR_FILE=target/ebr-bff-svc.jar
COPY ${JAR_FILE} ebr-bff-svc.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar ebr-bff-svc.jar"]