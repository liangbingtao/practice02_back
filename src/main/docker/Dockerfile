FROM java:8
VOLUME /tmp
ADD spring-boot-docker-0.0.1-SNAPSHOT.jar /test.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/test.jar"]


#FROM java:8
#VOLUME /tmp
#ADD spring-boot-docer-0.0.1-SNAPSHOT.jar /test.jar
#ENTRYPOINT ["java","-jar","/app.jar"]