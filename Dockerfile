FROM openjdk:8
EXPOSE 8082
ADD target/spring-rest-post.jar spring-rest-post.jar
ENTRYPOINT ["java", "-jar", "spring-rest-post.jar"]