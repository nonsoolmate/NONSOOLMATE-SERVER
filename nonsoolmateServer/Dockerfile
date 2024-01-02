FROM amd64/temurin:17

WORKDIR /app

COPY ./build/libs/nonsoolmateServer-0.0.1-SNAPSHOT.jar /app/nonsoolmateServer.jar

CMD ["java", "-Duser.timezone=Asia/Seoul", "-jar", "-Dspring.profiles.active=dev", "nonsoolmateServer.jar"]
