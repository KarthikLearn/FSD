FROM java:8-jre
WORKDIR usr/src
COPY ./target/eurekaservice-1.0.jar /usr/src/eurekaservice-1.0.jar
ENTRYPOINT ["java","-jar","eurekaservice-1.0.jar"]
CMD ["-start"]
