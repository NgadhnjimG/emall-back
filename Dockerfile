FROM openjdk:15
ADD target/emall-web-service.jar emall-web-service.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar","emall-web-service.jar"]