# template from: https://github.com/ktorio/ktor-samples/blob/master/deployment/docker/Dockerfile
FROM openjdk:8-jre-alpine

ENV APPLICATION_USER ktor
RUN adduser -D -g '' ${APPLICATION_USER}

# Then we add the user, create the /app folder and give permissions to our user.
RUN mkdir /app
RUN chown -R ${APPLICATION_USER} /app

# Marks this container to use the specified $APPLICATION_USER
USER ${APPLICATION_USER}

COPY ./build/libs/capstone-app.jar /app/capstone-app.jar
WORKDIR /app

CMD ["java", "-server", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-XX:InitialRAMFraction=2", "-XX:MinRAMFraction=2", "-XX:MaxRAMFraction=2", "-XX:+UseG1GC", "-XX:MaxGCPauseMillis=100", "-XX:+UseStringDeduplication", "-jar", "capstone-app.jar"]
