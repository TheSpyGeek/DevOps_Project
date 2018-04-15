FROM ubuntu:16.04
RUN apt-get -y update && apt-get -y install git && apt-get -y install openjdk-8-jdk && apt-get -y install maven
RUN git clone https://github.com/TheSpyGeek/DevOps_Project.git && cd DevOps_Project && mvn package
CMD java -jar target/JavaPanda-1.0-jar-with-dependencies.jar
