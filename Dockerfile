FROM openjdk:17-jdk-slim-buster

RUN mkdir -p /opt


COPY target/*.jar /opt/app.jar
EXPOSE 8080
CMD java -Xms${JAVA_XMS:-512m} -Xmx${JAVA_XMX:-3072m} -jar /opt/app.jar