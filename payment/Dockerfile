FROM openjdk:21
LABEL authors="cherhy"
ARG JAR_FILE=build/libs/payment.jar
COPY ${JAR_FILE} app.jar

ENV TZ=Asia/Seoul
ENV PROFILE dev

ENTRYPOINT ["java","-jar","/app.jar"]