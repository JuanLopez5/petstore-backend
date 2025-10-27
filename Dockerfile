FROM eclipse-temurin:17-jre
EXPOSE 8080
ADD target/petstore-backend.jar petstore-backend.jar
ENTRYPOINT ["java","-jar","/petstore-backend.jar"]
