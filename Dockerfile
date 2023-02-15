FROM openjdk:11-jdk
ARG JAR_FILE=build/libs/one_eye-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
COPY application.properties /home/opc/one_son_back/application.properties
COPY util /home/opc/one_son_back/src/main/java/com/example/one_eye/
ENTRYPOINT ["java","-jar","/app.jar"]
