FROM openjdk:21
LABEL authors="cherhy"
COPY ./build/libs/schedule.jar app.jar

ENV TZ=Asia/Seoul
ENV PROFILE dev

EXPOSE 10104

ENTRYPOINT ["java","-jar","/app.jar","-port=10104"]