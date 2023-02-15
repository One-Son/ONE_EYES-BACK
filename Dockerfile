FROM ubuntu:22.04
LABEL maintainer "seo_seung_jun <smdmim@gmail.com>"

RUN apt-get update && apt-get upgrade -y
RUN apt-get install openjdk-11-jdk -y
RUN echo "export JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java))))" >> /root/.bashrc
RUN echo "PATH=$PATH:$JAVA_HOME/bin" >> /root/.bashrc
RUN /bin/bash -c "source /root/.bashrc"

RUN mkdir -p /app
WORKDIR /app

COPY /var/lib/jenkins/workspace/oneyes/build/libs/one_eye-0.0.1-SNAPSHOT.jar /app/application.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/application.jar"]
