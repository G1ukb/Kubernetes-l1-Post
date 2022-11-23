FROM openjdk:8
EXPOSE 8082
ADD target/spring-post-0.0.1-SNAPSHOT.jar spring-rest-post.jar
ENTRYPOINT ["java", "-jar", "spring-rest-post.jar"]