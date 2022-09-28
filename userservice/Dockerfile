FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=userDb
ENV MYSQL_URL=jdbc:mysql://localhost:3306/userDb
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ADD ./target/userservice-1.0.jar /usr/src/userservice-1.0.jar
ENTRYPOINT ["java","-jar","userservice-1.0.jar"]
