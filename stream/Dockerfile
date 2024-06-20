FROM openjdk:21
LABEL authors="cherhy"
ARG JAR_FILE=build/libs/stream-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

ENV TZ=Asia/Seoul
ENV PROFILE dev

# TODO : ktor application 실행 방식으로 수정하기
ENTRYPOINT ["java","-jar","/app.jar"]
