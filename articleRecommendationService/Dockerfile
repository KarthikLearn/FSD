FROM java:8-jre
WORKDIR usr/src
ENV MONGO_DATABASE=recommendDB
ENV MONGO_URL=mongodb://localhost:27017/recommendDB
ADD ./target/articlerecommendservice-1.0.jar /usr/src/articlerecommendservice-1.0.jar
ENTRYPOINT ["java","-jar","articlerecommendservice-1.0.jar"]
