FROM frolvlad/alpine-oraclejdk8:slim
EXPOSE 8080
RUN mkdir -p /app/
ADD build/libs/productstore-spring-boot-0.1.0.jar /app/productstore-spring-boot-0.1.0.jar
ENTRYPOINT ["java", "-jar", "/app/productstore-spring-boot-0.1.0.jar"]