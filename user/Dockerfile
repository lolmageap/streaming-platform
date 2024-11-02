FROM openjdk:21
LABEL authors="cherhy"
COPY ./build/libs/user.jar app.jar

ENV TZ=Asia/Seoul
ENV PROFILE dev

EXPOSE 10103

ENTRYPOINT ["java","-jar","/app.jar", "-port=10103"]