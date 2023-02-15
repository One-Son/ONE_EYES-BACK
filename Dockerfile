FROM openjdk:11-jdk
LABEL maintainer="gmail.com"
ARG JAR_FILE=build/libs/one_eye-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} docker-springboot.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/docker-springboot.jar"]
