FROM openjdk:21
LABEL authors="cherhy"
COPY ./build/libs/user-all.jar app.jar

ENV TZ=Asia/Seoul
ENV PROFILE dev

ENTRYPOINT ["java","-jar","/app.jar"]